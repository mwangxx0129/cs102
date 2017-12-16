public class LinearDataStruct {
    public static String myToString(int[] a) {
        if (a == null)
            return "null";
        int iMax = a.length - 1;
        if (iMax == -1)
            return "[]";

        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; ; ++ i) {
            sb.append(a[i]);
            if (i == iMax)
                return sb.append("]").toString();
            sb.append(", ");
        }
    }
}
