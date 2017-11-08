package square.xmlparser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yingt on 2017/10/20.
 */
public class XmlNode {

    private String m_name, m_value;
    private short m_type;
    private List<XmlNodeAttribute> m_attrList;
    private List<XmlNode> m_children;

    public XmlNode(String name, short type) {
        this(name, type, null);
    }

    public XmlNode(String name, short type, String value) {
        m_name = name;
        m_value = value;
        m_type = type;

        m_attrList = null;
        m_children = null;
    }

    public void appendAttribute(XmlNodeAttribute attr) {
        if (m_attrList == null)
            m_attrList = new ArrayList<>();
        m_attrList.add(attr);
    }

    public void appendChild(XmlNode child) {
        if (m_children == null)
            m_children = new ArrayList<>();
        m_children.add(child);
    }
}
