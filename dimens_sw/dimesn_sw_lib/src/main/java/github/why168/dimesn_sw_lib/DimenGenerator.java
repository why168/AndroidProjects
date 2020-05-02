package github.why168.dimesn_sw_lib;


import java.io.File;


/**
 * DimenGenerator
 *
 * @author Edwin.Wu edwin.wu05@gmail.com
 * @version 2020/5/2 2:43 PM
 * @since JDK1.8
 */
public class DimenGenerator {

    // 375x812 iPhone使用流量暗示iPhone 8分辨率：2436x1125

    /**
     * 设计稿尺寸(将自己设计师的设计稿的宽度填入)
     */
    private static final int DESIGN_WIDTH = 375;

    /**
     * 设计稿的高度  （将自己设计师的设计稿的高度填入）
     */
    private static final int DESIGN_HEIGHT = 812;

    public static void main(String[] args) {
        int smallest = Math.min(DESIGN_WIDTH, DESIGN_HEIGHT);  //   求得最小宽度
        DimenTypes[] values = DimenTypes.values();
        for (DimenTypes value : values) {
            File file = new File("dimen_res");
            MakeUtils.makeAll(smallest, value, file.getAbsolutePath());
        }
    }

}
