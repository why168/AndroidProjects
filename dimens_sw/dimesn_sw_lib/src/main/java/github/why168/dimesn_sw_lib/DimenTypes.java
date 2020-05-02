package github.why168.dimesn_sw_lib;


/**
 * DimenTypes
 *
 * @author Edwin.Wu edwin.wu05@gmail.com
 * @version 2020/5/2 2:43 PM
 * @since JDK1.8
 */
public enum DimenTypes {

    //适配Android 3.2以上   大部分手机的sw值集中在  300-460之间

    DP_sw__300(300),  // values-sw300
    DP_sw__310(310),
    DP_sw__320(320),
    DP_sw__330(330),
    DP_sw__340(340),
    DP_sw__350(350),
    DP_sw__360(360),
    DP_sw__370(370),
    DP_sw__380(380),
    DP_sw__390(390),
    DP_sw__400(400),
    DP_sw__410(410),
    DP_sw__420(420),
    DP_sw__430(430),
    DP_sw__440(440),
    DP_sw__450(450),
    DP_sw__460(460),
    DP_sw__470(470),
    DP_sw__480(480),
    DP_sw__490(490),
    DP_sw__500(500),
    DP_sw__510(510),
    DP_sw__520(520),
    DP_sw__530(530),
    DP_sw__540(540),
    DP_sw__550(550),
    DP_sw__560(560),
    DP_sw__570(570),
    DP_sw__580(580),
    DP_sw__590(590),
    DP_sw__600(600);


    /**
     * 屏幕最小宽度
     */
    private int swWidthDp;

    DimenTypes(int swWidthDp) {
        this.swWidthDp = swWidthDp;
    }

    public int getSwWidthDp() {
        return swWidthDp;
    }

    public void setSwWidthDp(int swWidthDp) {
        this.swWidthDp = swWidthDp;
    }

}
