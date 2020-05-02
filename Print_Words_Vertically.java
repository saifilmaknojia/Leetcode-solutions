import java.util.*;

class Print_Words_Vertically {
    public List<String> printVertically(String s) {
        List<StringBuilder> list = new ArrayList<>();

        String[] strArray = s.split(" ");
        boolean isAdded = false;
        int j = 0;
        while (true) {
            int emptyCount = 0;
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < strArray.length; ++i) {
                if (strArray[i].length() > j) {
                    while (emptyCount > 0) {
                        --emptyCount;
                        sb.append(" ");
                    }
                    sb.append(strArray[i].charAt(j));
                    isAdded = true;
                } else {
                    ++emptyCount;
                }
            }
            if (isAdded && sb.length() != 0) {
                list.add(sb);
            }
            if (sb.length() == 0) {
                break;
            }
            ++j;

        }
        List<String> listResponse = new ArrayList<>();

        for (int i = 0; i < list.size(); ++i) {
            listResponse.add(list.get(i).toString());
        }

        return listResponse;
    }
}