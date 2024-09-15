package com.tebreca.slimed.manual.parser;

import com.tebreca.slimed.SlimedConfig;
import com.tebreca.slimed.manual.ManualEntry;
import com.tebreca.slimed.manual.parser.element.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.item.ItemStack;
import org.jdom2.*;
import org.jdom2.input.SAXBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class XmlPageParser implements PageParser {

    private static final Logger log = LoggerFactory.getLogger(XmlPageParser.class);
    private final Font font;
    private final static int spacing = 5;
    Consumer<MutableComponent> current;
    Consumer<PageElement> appender;
    private final List<ManualEntry.Page> pages = new ArrayList<>();

    public XmlPageParser(Font font) {
        this.font = font;
    }

    /**
     * Parses a JDOM2 XML document, and saves the pages to this parser
     *
     * @param document
     */
    public void parse(Document document) {
        List<PageElement> pageElements = new ArrayList<>();
        Element rootElement = document.getRootElement();
        appender = pageElements::add;
        descend(rootElement);
        if (rootElement.getAttribute("manualPaging") != null) {
            Iterator<PageElement> iterator = pageElements.iterator();
            boolean flag = true;
            List<PageElement> page = new ArrayList<>();
            while (flag) {
                if (iterator.hasNext()) {
                    PageElement next = iterator.next();
                    if (next != null) {
                        page.add(next);
                    } else {
                        pages.add(new VerticalElementPage(page.toArray(PageElement[]::new), spacing));
                        page.clear();
                    }
                } else {
                    if (!page.isEmpty())
                        pages.add(new VerticalElementPage(page.toArray(PageElement[]::new), spacing));
                    flag = false;
                }
            }
        } else {
            pageElements.removeIf(Objects::isNull);
            pages.add(new VerticalElementPage(pageElements.toArray(PageElement[]::new), spacing));//TODO: configurable
        }
    }

    private void descend(Element element) {
        List<MutableComponent> components;
        if (SlimedConfig.PARTY_POOPER_MODE.getAsBoolean() && element.getAttribute("lore") != null)
            return;
        switch (element.getName().toLowerCase(Locale.ROOT)) {
            case "title":
                components = contentToText(element.getContent());
                appender.accept(new HeaderElement(font, 1.7f, components.stream().map(Component::getVisualOrderText).reduce(FormattedCharSequence::composite).orElse(FormattedCharSequence.composite())));
                break;
            case "b":
                if (current == null) {
                    log.warn("No parent element defined for construction!: dropping bold element and it's contents...");
                } else {
                    components = contentToText(element.getContent());
                    components.stream().map(c -> c.setStyle(Style.EMPTY.withBold(true))).forEach(current);
                }
                break;
            case "i":
                if (current == null) {
                    log.warn("No parent element for construction!: dropping italics element and it's contents...");
                } else {
                    components = contentToText(element.getContent());
                    components.stream().map(c -> c.setStyle(Style.EMPTY.withItalic(true))).forEach(current);
                }
                break;
            case "block":
                components = contentToText(element.getContent());
                boolean center = element.getAttribute("centerText") != null;
                appender.accept(new TextBlockElement(FormattedText.composite(components.stream().map(m -> ((FormattedText) m)).toArray(FormattedText[]::new)), font, center));
                break;
            case "paragraph":
                components = contentToText(element.getContent());
                appender.accept(new ParagraphElement(FormattedText.composite(components.stream().map(m -> ((FormattedText) m)).toArray(FormattedText[]::new)), font));
                break;
            case "subscript":
                components = contentToText(element.getContent());
                appender.accept(new SubsrciptElement(FormattedCharSequence.composite(components.stream().map(MutableComponent::getVisualOrderText).toArray(FormattedCharSequence[]::new)), font));
                break;
            case "pagebreak":
                appender.accept(null);//Maybe more safe method?
                break;
            default:
                element.getChildren().forEach(this::descend);
        }
    }

    private List<MutableComponent> contentToText(List<Content> contents) {
        List<MutableComponent> components = new ArrayList<>();
        Iterator<Content> iterator = contents.iterator();
        Content previous = null;
        while (iterator.hasNext()) {
            Content c = iterator.next();
            switch (c.getCType()) {
                case Comment, ProcessingInstruction, EntityRef, DocType, CDATA -> {
                }
                case Element -> {
                    Consumer<MutableComponent> old = current;
                    current = components::add;
                    descend((Element) c);
                    current = old;
                }
                case Text ->
                        components.add(Component.literal(((previous != null && previous.getCType() == Content.CType.Element) ? " " : "") + ((Text) c).getTextNormalize().replace("\n", "") + (iterator.hasNext() ? " " : "")));
            }
            previous = c;
        }
        return components;
    }

    @Override
    public ManualEntry.Page[] generate() {
        return pages.toArray(ManualEntry.Page[]::new);
    }

    public static ManualEntry createFrom(String path, Predicate<ItemStack> stackPredicate) {
        try (InputStream stream = XmlPageParser.class.getResourceAsStream(path)) {
            SAXBuilder saxBuilder = new SAXBuilder();
            XmlPageParser parser = new XmlPageParser(Minecraft.getInstance().font);
            parser.parse(saxBuilder.build(stream));
            return new ManualEntry(stackPredicate, parser.generate());
        } catch (IOException | JDOMException e) {
            log.error("Failed to read ManualEntry from internal file at: {}", path, e);
        }
        return null;
    }

}
