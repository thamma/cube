package me.thamma.cube.model.regex;

import me.thamma.cube.algorithmInterpreter.lexer.Token;
import me.thamma.cube.model.Cube;
import me.thamma.cube.model.CubeConstants;
import me.thamma.cube.model.Sticker;
import me.thamma.utils.CubeUtils;

import java.security.cert.CollectionCertStoreParameters;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class CubeRegex {

    public RegexToken[] pattern;
    private RegexToken[] exactMatch;

    public CubeRegex() {
        this.pattern = new RegexToken[54];
        this.exactMatch = new RegexToken[54];
        Arrays.fill(this.pattern, RegexToken.ANY);
    }

    /**
     * @param pattern
     * @return
     */
    public static CubeRegex compile(String pattern) throws Exception {
        CubeRegex cubeRegex = new CubeRegex();
        cubeRegex.parse(pattern);
        return cubeRegex;
    }

    private void parse(String pattern) throws Exception {
        String input = new String(pattern.toCharArray()).toUpperCase().replaceAll("\\s+", "");
        int slot = 0;
        while (input.length() > 0) {
            if (input.charAt(0) == '(') {
                if (!input.contains(")")) throw new Exception("no ')'");
                String inner = input.substring(1, input.indexOf(")"));
                input = input.replaceFirst(String.format("\\(%s\\)", inner), "");
                if (!inner.contains(":")) {
                    int factor = -1;
                    for (int i = 0; i < input.length(); i++) {
                        char c = input.charAt(i);
                        if (c < '0' || c > '9') break;
                        factor = (factor == -1 ? 0 : factor) * 10 + (c - '0');
                    }
                    input = input.replace("" + factor, "");
                    if (factor == -1) factor = 1;
                    for (int i = 0; i < factor * inner.length(); i++) {
                        RegexToken token = RegexToken.fromRegex("" + inner.charAt(i % inner.length()));
                        if (token == null) throw new Exception("invalid token at " + inner.charAt(i % inner.length()));
                        this.pattern[slot++] = token;
                    }
                } else {
                    String[] clauses = inner.split(",");
                    for (String clause : clauses) {
                        if (clause.contains(":")) {
                            String[] mapping = clause.split(":");
                            if (mapping.length != 2) throw new Exception("not exact mapping: " + clause);
                            RegexToken token = RegexToken.fromRegex(mapping[1]);
                            if (token == null)
                                throw new Exception("Token null in mapping: " + clause);
                            if (!CubeUtils.isValidSticker(mapping[0]))
                                throw new Exception("Invalid Sticker: " + clause);
                            Sticker sticker = Sticker.valueOf(mapping[0]);
                            this.exactMatch[sticker.ordinal()] = token;
                        }
                    }
                }
            } else {
                RegexToken token = RegexToken.fromRegex("" + input.charAt(0));
                input = input.substring(1);
                if (token == null)
                    throw new Exception("illegal token found: " + input.charAt(0));
                this.pattern[slot++] = token;
            }
        }
    }

    public String toString() {
        return String.format("%s\n%s\n", Arrays.toString(this.pattern), Arrays.toString(this.exactMatch));
    }

    public boolean matches(Cube cube) {
        for (int i = 0; i < Sticker.values().length; i++) {
            Sticker sticker = Sticker.values()[i];
            if (this.exactMatch[i] == null) {
                if (this.pattern[i] != null && !this.pattern[i].matches(cube.getCurrentStickerAt(sticker))) {
                    return false;
                }
            } else if (!this.exactMatch[i].matches(cube.getCurrentStickerAt(sticker))) {
                return false;
            }
        }
        return true;
    }
}
