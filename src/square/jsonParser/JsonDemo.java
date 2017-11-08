package square.jsonParser;

/**
 * Created by yingtan on 10/25/17.
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yingt on 2017/10/25.
 */

public class JsonDemo {

    public void serialize(List<Object> data) {
        try {
            String jsonString = generateJson_4_list(data);

            // Java open file for writing ...
            // write jsonString to file
        }
        catch (IOException ex) {
            // handle ex
        }
        catch (Exception ex) {
            // handle ex
        }
    }

    private String generateJson_4_list(List list) throws Exception {

        StringBuffer sb = new StringBuffer();
        boolean head = true;
        for (Object obj: list) {
            if (head) {
                sb.append("[");
                head = false;
            }
            else {
                sb.append(", ");
            }

            if (isString(obj))  // TODO
                sb.append("<" + (String) obj + ">");
            else if (isList(obj))  // TODO
                sb.append(generateJson_4_list((List) obj));
            else
                throw new Exception("Unknown type ...");
        }

        sb.append("]");
        return sb.toString();
    }

    public Object parse(String filePath) {
        try {
            // Java open file for reading ...
            String fileContent = "";  // TODO
            return parseJson(fileContent);
        }
        catch (IOException ex) {
            // handle ex
        }
        catch (Exception ex) {
            // handle ex
        }

        return null;
    }

    private Object parseJson(String input) throws Exception {
        if (input == null) return null;

        String content = input.trim();
        if (content.isEmpty()) return null;

        int length = content.length();
        if (length < 2)
            throw new Exception("Illegal Json ...");

        if (content.charAt(0) == '<' && content.charAt(length - 1) == '>') {
            if (length == 2)
                return "";  // AS empty string
            else
                return content.substring(1, length - 1).trim();  // AS string
        }

        if (content.charAt(0) != '[' || content.charAt(length - 1) != ']')
            throw new Exception("Illegal Json ...");

        if (length == 2) return new ArrayList<>();

        content = content.substring(1, length - 1).trim();
        if (content.isEmpty()) return new ArrayList<>();

        return parseJson_4_list(content.substring(1, length - 1));
    }

    private List<Object> parseJson_4_list(String stringList) throws Exception {
        List<Object> result = new ArrayList<>();

        String content = stringList.trim();
        if (content.isEmpty()) return result;

        int index = findFirstSep(content);
        if (index < 0) {
            Object obj = parseJson(content);
            result.add(obj);
        }
        else {
            Object obj = parseJson(content.substring(0, index));
            result.add(obj);

            if (content.length() > index + 1) {
                List<Object> remains = parseJson_4_list(content.substring(index + 1));
                if (remains != null) {
                    result.addAll(remains);
                    remains.clear();
                }
            }
        }

        return result;
    }

    private int findFirstSep(String content) {
        int result = -1;
        int level = 0;
        for (int i = 0; i < content.length(); i++) {
            char ch = content.charAt(i);
            if (ch == ',') {
                if (level == 0) {
                    result = i;
                    break;
                }
            }
            else if ( (ch == '[') || (ch == '<') )
                level++;
            else if ( (ch == ']') || (ch == '>') )
                level--;
        }

        return result;
    }

    boolean isString(Object obj) {
         return true;
    }

    boolean isList(Object obj) {
         return true;
    }
}

