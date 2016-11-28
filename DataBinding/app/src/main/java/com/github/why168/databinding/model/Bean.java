package com.github.why168.databinding.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.github.why168.databinding.BR;

import java.util.List;

/**
 * 实体类
 *
 * @author Edwin.Wu
 * @version 2016/11/28 00:39
 * @since JDK1.8
 */
public class Bean extends BaseObservable {

    /**
     * data : [{"title":"聆听经典","subtitle":"耳熟能详，百听不厌","summary":"","url":"beva://album?act=play&id=54","img":"http://zzya.beva.cn/img/Fs6GezTvPdE-RL-GZ_1zWTG0dOaL.png","img_thumb":"","gmt_pub":1440137883,"gmt_end":1499401048,"paid":0},{"title":"原创儿歌新动力","subtitle":"小朋友自己的流行乐","summary":"","url":"beva://album?act=play&id=57","img":"http://zzya.beva.cn/img/Fs2m_VBy7LcsvhfZsRL88jlKTBQu.png","img_thumb":"","gmt_pub":1440759420,"gmt_end":1480479448,"paid":0},{"title":"地球小卫士","subtitle":"4.22 世界地球日","summary":"","url":"beva://album?act=play&id=127","img":"http://zzya.beva.cn/img/Fjka01NZW2ooRIq98U9mLbpfU5bn.png","img_thumb":"","gmt_pub":1461248542,"gmt_end":1585584000,"paid":0},{"title":"世界读书日","subtitle":"有爱生活阅读童年","summary":"","url":"beva://album?act=play&id=148","img":"http://zzya.beva.cn/img/Ft35u7KgUtdqfsvQUjoIdZSg8-ph.png","img_thumb":"","gmt_pub":1461293069,"gmt_end":1575043200,"paid":2},{"title":"小鸟爱唱歌","subtitle":"跟着小鸟欢乐唱儿歌","summary":"","url":"beva://album?act=play&id=156","img":"http://zzya.beva.cn/img/Fn2f5ytAGQrhhjPdNrOG5BlUnjai.png","img_thumb":"","gmt_pub":1460445527,"gmt_end":1586620800,"paid":0},{"title":"快乐律动","subtitle":"幼儿舞蹈天天学","summary":"","url":"beva://album?act=play&id=8","img":"http://zzya.beva.cn/img/ac_hz_8.png","img_thumb":"","gmt_pub":1440759420,"gmt_end":1522469848,"paid":2},{"title":"好好吃饭儿歌","subtitle":"养成独立吃饭好习惯","summary":"","url":"beva://album?act=play&id=111","img":"http://zzya.beva.cn/img/Fh9IGhn4YAETCi_G5irOD0vEGUg6.png","img_thumb":"","gmt_pub":1448294400,"gmt_end":1548172800,"paid":0},{"title":"春游好儿歌","subtitle":"踏青出游，哄娃必备","summary":"","url":"beva://album?act=play&id=153","img":"http://zzya.beva.cn/img/FiNciCluhMQ23om2-M3usn528BU1.png","img_thumb":"","gmt_pub":1458700470,"gmt_end":1574438400,"paid":0},{"title":"爱笑的宝宝听什么","subtitle":"快乐儿歌唱不停","summary":"","url":"beva://album?act=play&id=122","img":"http://zzya.beva.cn/img/Fg9Ffci_j3EaKwqmibC2MyJPEG9w.png","img_thumb":"","gmt_pub":1450689554,"gmt_end":1517328000,"paid":0},{"title":"玩疯了-手工帝","subtitle":"每天一个陪玩高招","summary":"","url":"beva://album?act=play&id=128","img":"http://zzya.beva.cn/img/Fv15tfU5EXF86DZtPq5IJ7JYRf2n.png","img_thumb":"","gmt_pub":1451389243,"gmt_end":1517328000,"paid":0},{"title":"妈妈你最美","subtitle":"魅力辣妈，轻松带娃","summary":"","url":"beva://album?act=play&id=149","img":"http://zzya.beva.cn/img/Fog504dndQnKSIVlfYJuh6Zq0Nge.png","img_thumb":"","gmt_pub":1457075002,"gmt_end":1551682997,"paid":0},{"title":"谷建芬儿歌集","subtitle":"儿歌，是最好的陪伴","summary":"","url":"beva://album?act=play&id=131","img":"http://zzya.beva.cn/img/FjXAv01LqhVZFUu0w5frIVegkFXg.png","img_thumb":"","gmt_pub":1452928173,"gmt_end":1478102400,"paid":0},{"title":"最受欢迎习惯儿歌","subtitle":"播放过亿习惯儿歌集","summary":"","url":"beva://album?act=play&id=51","img":"http://zzya.beva.cn/img/FsVidGVjLuFNhTT0MFq7kABebbbm.png","img_thumb":"","gmt_pub":1452912554,"gmt_end":1509379200,"paid":0},{"title":"最受欢迎亲子儿歌","subtitle":"播放量过亿亲子合辑","summary":"","url":"beva://album?act=play&id=52","img":"http://zzya.beva.cn/img/Fg2PiZya8o5ynN0JJFLAp--OJyam.png","img_thumb":"","gmt_pub":1452911800,"gmt_end":1527696000,"paid":0},{"title":"叶圣涛儿歌集","subtitle":"有儿歌，每天都是盛典","summary":"","url":"beva://album?act=play&id=133","img":"http://zzya.beva.cn/img/FvgQXnzi3ha2b0UauJnzkR9LuY78.png","img_thumb":"","gmt_pub":1452928289,"gmt_end":1488211200,"paid":0},{"title":"打针吃药我不怕","subtitle":"帮宝宝克服打针恐惧","summary":"","url":"beva://album?act=play&id=117","img":"http://zzya.beva.cn/img/FhV587OYXfg7x9e52ZBRViCVQpPj.png","img_thumb":"","gmt_pub":1450087216,"gmt_end":1537027200,"paid":0},{"title":"我和小树比高高","subtitle":"拥抱春天，播种绿色","summary":"","url":"beva://album?act=play&id=127","img":"http://zzya.beva.cn/img/FqaOn4h7e4r3wJj-hmWvsQKpY5rX.png","img_thumb":"","gmt_pub":1451389355,"gmt_end":1517328000,"paid":0},{"title":"嗨翻猴年","subtitle":"猴年猴嗨森","summary":"","url":"beva://album?act=play&id=139","img":"http://zzya.beva.cn/img/Fm-XHIZB_dVjsb_2546QJIsWTp5M.png","img_thumb":"","gmt_pub":1454394905,"gmt_end":1488211200,"paid":0},{"title":"儿歌盛典-十大金曲","subtitle":"亿万儿童的真实选择","summary":"","url":"beva://album?act=play&id=137","img":"http://zzya.beva.cn/img/FsDXdHcxpyTyi7q7tewCNUGbudhC.png","img_thumb":"","gmt_pub":1454395004,"gmt_end":1480608000,"paid":0},{"title":"科学小超人新春版","subtitle":"小超人，大拜年","summary":"","url":"beva://album?act=play&id=135","img":"http://zzya.beva.cn/img/FqDdS8ziACcBsFUpkei8JFJ5SEAY.png","img_thumb":"","gmt_pub":1454396400,"gmt_end":1485792000,"paid":0},{"title":"我爱小白牙","subtitle":"今天你刷牙了吗","summary":"","url":"beva://album?act=play&id=121","img":"http://zzya.beva.cn/img/FhAFc2e_zR2oNkwjWGgZeunv4w2-.png","img_thumb":"","gmt_pub":1451990805,"gmt_end":1522425600,"paid":0},{"title":"冬夜好梦儿歌","subtitle":"最长的夜，愿你好梦","summary":"","url":"beva://album?act=play&id=101","img":"http://zzya.beva.cn/img/ac_hz_101.png","img_thumb":"","gmt_pub":1446393600,"gmt_end":1514649600,"paid":0},{"title":"喵星人vs汪星人","subtitle":"萌猫萌狗儿歌大集合","summary":"","url":"beva://album?act=play&id=113","img":"http://zzya.beva.cn/img/FoLrz8I9OZYS2vY3mnwq3XRtpBId.png","img_thumb":"","gmt_pub":1448950143,"gmt_end":1477929600,"paid":0},{"title":"一起来玩雪","subtitle":"大雪时节，一起来玩雪","summary":"","url":"beva://album?act=play&id=112","img":"http://zzya.beva.cn/img/Fm8PuyQ_vfDkLBbbWx2Qc9CjCC5e.png","img_thumb":"","gmt_pub":1449417600,"gmt_end":1514649600,"paid":0},{"title":"唱歌学数数","subtitle":"12345，快来数一数","summary":"","url":"beva://album?act=play&id=55","img":"http://zzya.beva.cn/img/ac_hz_55.png","img_thumb":"","gmt_pub":1440137883,"gmt_end":1514693848,"paid":0},{"title":"好宝宝，不赖床","subtitle":"用儿歌叫醒美好一天","summary":"","url":"beva://album?act=play&id=103","img":"http://zzya.beva.cn/img/ac_hz_103.png","img_thumb":"","gmt_pub":1446307200,"gmt_end":1514649600,"paid":0}]
     */

    private List<DataBean> data;

    public void setData(List<DataBean> data) {
        this.data = data;
        notifyPropertyChanged(BR.data);
    }

    @Bindable
    public List<DataBean> getData() {
        return data;
    }

    public static class DataBean extends BaseObservable {
        /**
         * title : 聆听经典
         * subtitle : 耳熟能详，百听不厌
         * summary :
         * url : beva://album?act=play&id=54
         * img : http://zzya.beva.cn/img/Fs6GezTvPdE-RL-GZ_1zWTG0dOaL.png
         * img_thumb :
         * gmt_pub : 1440137883
         * gmt_end : 1499401048
         * paid : 0
         */

        private String title;
        private String subtitle;
        private String summary;
        private String url;
        private String img;
        private String img_thumb;
        private int gmt_pub;
        private int gmt_end;
        private int paid;

        public void setTitle(String title) {
            this.title = title;
            notifyPropertyChanged(BR.title);
        }

        public void setSubtitle(String subtitle) {
            this.subtitle = subtitle;
            notifyPropertyChanged(BR.subtitle);
        }

        public void setSummary(String summary) {
            this.summary = summary;
            notifyPropertyChanged(BR.summary);
        }

        public void setUrl(String url) {
            this.url = url;
            notifyPropertyChanged(BR.url);
        }

        public void setImg(String img) {
            this.img = img;
            notifyPropertyChanged(BR.img);
        }

        public void setImg_thumb(String img_thumb) {
            this.img_thumb = img_thumb;
            notifyPropertyChanged(BR.img_thumb);
        }

        public void setGmt_pub(int gmt_pub) {
            this.gmt_pub = gmt_pub;
            notifyPropertyChanged(BR.gmt_pub);
        }

        public void setGmt_end(int gmt_end) {
            this.gmt_end = gmt_end;
            notifyPropertyChanged(BR.gmt_end);
        }

        public void setPaid(int paid) {
            this.paid = paid;
            notifyPropertyChanged(BR.paid);
        }

        @Bindable
        public String getTitle() {
            return title;
        }

        @Bindable
        public String getSubtitle() {
            return subtitle;
        }

        @Bindable
        public String getSummary() {
            return summary;
        }

        @Bindable
        public String getUrl() {
            return url;
        }

        @Bindable
        public String getImg() {
            return img;
        }

        @Bindable
        public String getImg_thumb() {
            return img_thumb;
        }

        @Bindable
        public int getGmt_pub() {
            return gmt_pub;
        }

        @Bindable
        public int getGmt_end() {
            return gmt_end;
        }

        @Bindable
        public int getPaid() {
            return paid;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "title='" + title + '\'' +
                    ", subtitle='" + subtitle + '\'' +
                    ", summary='" + summary + '\'' +
                    ", url='" + url + '\'' +
                    ", img='" + img + '\'' +
                    ", img_thumb='" + img_thumb + '\'' +
                    ", gmt_pub=" + gmt_pub +
                    ", gmt_end=" + gmt_end +
                    ", paid=" + paid +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "Bean{" +
                "data=" + data +
                '}';
    }
}
