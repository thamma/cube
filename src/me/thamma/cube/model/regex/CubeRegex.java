package me.thamma.cube.model.regex;

import me.thamma.cube.model.Cube;
import me.thamma.cube.model.CubeConstants;
import me.thamma.cube.model.Sticker;

public class CubeRegex {

    private RegexToken[] pattern;
    private RegexToken[] exactMatch;

    CubeRegex(RegexToken[] pattern) {
        this.pattern = pattern;
    }

    public CubeRegex() {

    }

    /**
     * @param pattern
     * @return
     */
    public static CubeRegex compile(String pattern) {
        return new CubeRegex();
    }

    public boolean matches(Cube cube) {
        Sticker[] defaultFaceletDefinition = CubeConstants.Stickers.defaultFaceletDefinition;
        for (int i = 0; i < defaultFaceletDefinition.length; i++) {
            Sticker sticker = defaultFaceletDefinition[i];
            if (this.exactMatch[i] == (null)) {
                if (this.pattern[i] != null && !this.pattern[i].matches(cube.getCurrentStickerAt(sticker)))
                    return false;
            } else if (!this.exactMatch[i].matches(cube.getCurrentStickerAt(sticker)))
                return false;
        }
        return true;
    }
}
