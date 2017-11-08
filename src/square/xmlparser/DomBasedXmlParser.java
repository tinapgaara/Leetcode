package square.xmlparser;

/**
 * Created by yingtan on 10/19/17.
 */

/**
 * Created by yingt on 2017/10/19.
 */

/**
 *
 * 根据一个maven pom url作为root，找到里面的dependency, 比如这个
 * http://central.maven.org/maven2/org/apache/spark/spark-core_2.10/2.2.0/spark-core_2.10-2.2.0.pom
 * 根据group id，version，和 artifact id，parse成一个entity，这个entity还有其他的子entity，
 * 也就是dependency project pom 要求打印出来一种类似于UNIX文件系统的 directory path tree，
 * 其实本质上就是一个n-ary tree 遍历
 *
 */

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class DomBasedXmlParser {

    private DocumentBuilderFactory m_builderFactory;

    public DomBasedXmlParser() {
        m_builderFactory = DocumentBuilderFactory.newInstance();
    }


    // Load and parse XML file into DOM
    public Document parse(String filePath) {
        Document document = null;
        try {
            // DOM parser instance
            DocumentBuilder builder = m_builderFactory.newDocumentBuilder();
            // parse an XML file into a DOM tree
            document = builder.parse(new File(filePath));
        }
        catch (ParserConfigurationException e) {
            // handle exception here ...
            e.printStackTrace();
        }
        catch (SAXException e) {
            // handle exception here ...
            e.printStackTrace();
        }
        catch (IOException e) {
            // handle exception here ...
            e.printStackTrace();
        }
        return document;
    }

    public static void main(String[] args) {
        String xmlFilePath = "./src/xmlParser/res/spark-core_2.10-2.2.0.pom";
        int maxDepth = 6;
        String specifiedTagName = "*";
        String specifiedAttrName = "xmlns:xsi";

        DomBasedXmlParser parser = new DomBasedXmlParser();
        Document document = parser.parse(xmlFilePath);
        // get root element
        Element rootElement = document.getDocumentElement();

        XmlNode root = new XmlNode(rootElement.getNodeName(), rootElement.getNodeType());

        traverseElement(rootElement, maxDepth, 1, root);

        NodeList nodeList = rootElement.getElementsByTagName(specifiedTagName);
        String attrValue = rootElement.getAttribute(specifiedAttrName);
        if (nodeList != null) {
            int nodeCount = nodeList.getLength();
            for (int i = 0 ; i < nodeCount; i++) {
                Element element = (Element) nodeList.item(i);
                attrValue = element.getAttribute(specifiedAttrName);
                System.out.println("---- nodeName(" + element.getNodeName() +
                        "), nodeType(" + nodeTypeString(element.getNodeType()) +
                        "), attrValue(" + attrValue + ")");
            }
        }
    }

    private static void traverseElement(Element element, int depth, int level, XmlNode xmlNode) {
        String prefix = "";
        describe(level, prefix, element.getNodeName(), element.getNodeType(), null);

        // get all attributes of this element
        NamedNodeMap map = element.getAttributes();
        prefix = "Attribute: ";
        for (int index = 0; index < map.getLength(); index++) {
            Node attribute = map.item(index);
            String attrName = attribute.getNodeName();
            short attrNodeType = attribute.getNodeType();
            String attrValue = attribute.getNodeValue();
            if (xmlNode != null) {
                XmlNodeAttribute xnAttr = new XmlNodeAttribute(attrName, attrValue);
                xmlNode.appendAttribute(xnAttr);
            }
            describe(level, prefix, attrName, attrNodeType, attrValue);
        }

        if (depth <= 0) return;

        XmlNode xmlNode_child;

        // traverse child elements
        NodeList nodes = element.getChildNodes();
        for (int i = 0; i < nodes.getLength(); i++) {
            xmlNode_child = null;
            Node node = nodes.item(i);
            short nodeType = node.getNodeType();
            switch (nodeType) {
                case Node.ELEMENT_NODE:
                    Element child = (Element) node;

                    if (xmlNode != null) {
                        xmlNode_child = new XmlNode(child.getNodeName(), child.getNodeType());
                        xmlNode.appendChild(xmlNode_child);
                    }

                    // process child element
                    traverseElement(child, depth - 1, level + 1, xmlNode_child);
                    break;

                case Node.ATTRIBUTE_NODE:
                    // process attribute node
                    break;

                case Node.TEXT_NODE:
                    String nodeValue = node.getNodeValue();
                    if ( ! isEmptyValue(nodeValue) ) {
                        String nodeName = node.getNodeName();
                        if (xmlNode != null) {
                            xmlNode_child = new XmlNode(nodeName, nodeType, nodeValue);
                            xmlNode.appendChild(xmlNode_child);
                        }

                        describe(level + 1, "", nodeName, nodeType, nodeValue);
                    }

                    // process text node
                    break;

                case Node.CDATA_SECTION_NODE:
                    // process CDATA section node
                    break;

                case Node.ENTITY_REFERENCE_NODE:
                    // process entity ref node
                    break;

                case Node.ENTITY_NODE:
                    // process entity node
                    break;

                case Node.PROCESSING_INSTRUCTION_NODE:
                    // process PROCESSING_INSTRUCTION_NODE
                    break;

                case Node.COMMENT_NODE:
                    // process COMMENT_NODE
                    break;

                case Node.DOCUMENT_NODE:
                    // process DOCUMENT_NODE
                    break;

                case Node.DOCUMENT_TYPE_NODE:
                    // process DOCUMENT_TYPE_NODE
                    break;

                case Node.DOCUMENT_FRAGMENT_NODE:
                    // process DOCUMENT_FRAGMENT_NODE
                    break;

                case Node.NOTATION_NODE:
                    // process NOTATION_NODE
                    break;
            }
        }
    }

    private static boolean isEmptyValue(String value) {
        if (value == null)
            return true;
        return value.trim().isEmpty();
    }

    public static void describe(int level, String prefix, String nodeName, short nodeType, String nodeValue) {
        System.out.println(build_description(level, prefix, nodeName, nodeType, nodeValue));
    }

    private static String build_description(int level, String prefix, String nodeName, short nodeType, String nodeValue) {
        String spaces = "    ";
        String leading = "";
        for (int i = 0; i < level; i++)
            leading = leading + spaces;
        String result = leading + prefix + nodeName + "(" + nodeTypeString(nodeType) + ")";
        if (nodeValue == null)
            return result;
        else {
            nodeValue = nodeValue.trim();
            if (nodeValue.isEmpty())
                return result;
            else
                return result + ", value(" + nodeValue + ")";
        }
    }

    private static String nodeTypeString(short nodeType) {
        String typeString = "Unknown";
        switch (nodeType) {
            case Node.ELEMENT_NODE:
                typeString = "ELEMENT_NODE";
                break;

            case Node.ATTRIBUTE_NODE:
                typeString = "ELEMENT_NODE";
                break;

            case Node.TEXT_NODE:
                typeString = "ELEMENT_NODE";
                break;

            case Node.CDATA_SECTION_NODE:
                typeString = "ELEMENT_NODE";
                break;

            case Node.ENTITY_REFERENCE_NODE:
                typeString = "ELEMENT_NODE";
                break;

            case Node.ENTITY_NODE:
                typeString = "ELEMENT_NODE";
                break;

            case Node.PROCESSING_INSTRUCTION_NODE:
                typeString = "ELEMENT_NODE";
                break;

            case Node.COMMENT_NODE:
                typeString = "ELEMENT_NODE";
                break;

            case Node.DOCUMENT_NODE:
                typeString = "ELEMENT_NODE";
                break;

            case Node.DOCUMENT_TYPE_NODE:
                typeString = "ELEMENT_NODE";
                break;

            case Node.DOCUMENT_FRAGMENT_NODE:
                typeString = "ELEMENT_NODE";
                break;

            case Node.NOTATION_NODE:
                typeString = "ELEMENT_NODE";
                break;
        }
        return typeString;
    }
}
