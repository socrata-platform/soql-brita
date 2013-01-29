package com.socrata.soql.brita;

abstract class AbstractIdentifierFilter {
    private static final int SyntheticUnderscore = Integer.MAX_VALUE;
    private static final int StartOfString = Integer.MAX_VALUE - 1;
    private static final int EndOfString = Integer.MAX_VALUE - 2;

    private final int[] buf;
    private int endPtr;

    AbstractIdentifierFilter(scala.collection.Iterable<String> xs) {
        buf = initialFill(xs);
        endPtr = buf.length;
    }

    public String go() {
        collapseRuns();
        removeAdjacent();

        final String asString = convertBack();

        // Ok.  We're almost done.  Two edge cases and we can return it.
        if(asString.isEmpty()) return "_";
        else if(!isGoodStartCharacter(asString.charAt(0))) return "_" + asString;
        else return asString;
    }

    protected abstract boolean isGoodStartCharacter(char c);
    protected abstract boolean isGoodCharacter(char c);
    protected abstract boolean isUnderscoreish(int c);

    private boolean adjacentToBoundary(int i) {
        return isUnderscoreish(i) || i == StartOfString || i == EndOfString;
    }


    private void collapseRuns() {
        final int e = endPtr;
        final int[] b = buf;
        int src = 0;
        int dst = 0;
        while(src != e) {
            int here = b[src++];

            if(here == SyntheticUnderscore) {
                while(src != e && b[src] == SyntheticUnderscore) src += 1;
            }

            b[dst++] = here;
        }

        endPtr = dst;
    }

    private void removeAdjacent() {
        final int[] b = buf;
        final int e = endPtr;
        int src = 0;
        int dst = 0;
        boolean lastWasAdjacentTarget = false;
        while(src != e) {
            int here = b[src++];

            if(lastWasAdjacentTarget && here == SyntheticUnderscore) {
                // do nothing; lastWasAdjacentTarget will remain true
            } else {
                if(here == SyntheticUnderscore && (src != e) && adjacentToBoundary(b[src])) {
                    // still do nothing because the next thing is the neighbor
                } else {
                    lastWasAdjacentTarget = adjacentToBoundary(here);
                    b[dst++] = here;
                }
            }
        }

        endPtr = dst;
    }

    private String convertBack() {
        final int[] b = buf;
        final int e = endPtr - 1;
        StringBuilder sb = new StringBuilder(e - 1);
        for(int i = 1; i != e; ++i) {
            int c = b[i];
            sb.append(c == SyntheticUnderscore ? '_' : (char)c);
        }
        return sb.toString();
    }

    private int[] initialFill(final scala.collection.Iterable<String> xs) {
        final int[] buf = new int[totalLength(xs) + xs.size()-1 + 2];
        buf[0] = StartOfString;

        final scala.collection.Iterator<String> it = xs.iterator();
        int i = 1;
        while(it.hasNext()) {
            final String str = it.next();
            i = fillPart(str, buf, i);
            if(it.hasNext()) {
                buf[i++] = SyntheticUnderscore;
            }
        }

        buf[i] = EndOfString;

        return buf;
    }

    private int fillPart(final String s, final int[] buf, final int offs) {
        for(int i = 0; i != s.length(); ++i) {
            char c = s.charAt(i);
            buf[offs + i] = isGoodCharacter(c) ? c :SyntheticUnderscore;
        }
        return offs + s.length();
    }

    private static int totalLength(scala.collection.Iterable<String> xs) {
        final scala.collection.Iterator<String> it = xs.iterator();
        int total = 0;
        while(it.hasNext()) {
            total += it.next().length();
        }
        return total;
    }
}
