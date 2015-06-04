package com.example.billysusanto.qrnavigation_v2.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p/>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    public static List<DummyItem> ITEMS = new ArrayList<DummyItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();

/*
    public static String []slatLang = new String [] {"-6.890388", "107.610272"};
    public static String [][]dlatLang = new String [][] {{"-6.88950", "107.61265"}, {"-6.869319", "107.604135"}, {"-6.923640" ,"107.623833"}, {"-6.923640" ,"107.623833"}, {"-6.923640" ,"107.623833"}};
    public static String [][]detail = new String [][] {
            {"Burger Lapar", "Nikmati Promo Burger Terbaru kami\n\nhanya dengan Rp. 12.999,--\n\nDapatkan di outlet terdekat kami di kota anda\n\n"},
            {"Ayam Pedas", "Bagi Penikmat Makanan Pedas,\ntelah hadir dikota anda.\n\nAyam Pedas.\n\nPromo Teh Botoh bagi 1000 Pengunjung Pertama\n\n"},
            {"Nasi Uduk Enak" ,"Nasi Uduk Gitu-Gitu Aja?\nCobain Nasi Uduk disini,\n\nNasi Uduk Enak!\n\nwalau Mahal Tapi Enak..\n\n"},
            {"Minuman Segarrrr" ,"Udara Panas, Kepanasan\nHarus Banyak Minum,\n\nMinuman Segarrrr!\n\nTersegarrr yang pernah anda rasakan\n\n"},
            {"Semua Ada" ,"Mau Makan Apa?\nMau Minum Apa?\n\nSemua Ada!\n\nHarga Terjangkau\n\n"}};

    public static String alamat[] = new String [] {
            "Jln.Asal Asal no 12\n" +
                    "Bandung\n" +
                    "Indonesia",
            "Jln.Cari Makan no 109\n" +
                    "Bandung\n" +
                    "Indonesia",
            "Jln.Laper Banget no 7\n" +
                    "Bandung\n" +
                    "Indonesia",
            "Jln.Haus Terus no 76\n" +
                    "Bandung\n" +
                    "Indonesia",
            "Jln.Tambah Baru no 100\n" +
                    "Bandung\n" +
                    "Indonesia"
    };
*/
    static {
        // Add 3 sample items.
        addItem(new DummyItem("1",
                "Burger Lapar",
                "Nikmati Promo Burger Terbaru kami\n\nhanya dengan Rp. 12.999,--\n\nDapatkan di outlet terdekat kami di kota anda",
                "Jln.Asal Asal no 12\n" + "Bandung\n" + "Indonesia",
                "-6.890388", "107.610272",
                "-6.88950", "107.61265"
        ));
        addItem(new DummyItem("2",
                "Ayam Pedas",
                "Bagi Penikmat Makanan Pedas,\ntelah hadir dikota anda.\n\nAyam Pedas.\n\nPromo Teh Botoh bagi 1000 Pengunjung Pertama\n\n",
                "Jln.Cari Makan no 109\n" + "Bandung\n" +"Indonesia",
                "-6.890388", "107.610272",
                "-6.869319", "107.604135"
        ));
        addItem(new DummyItem("3",
                "Nasi Uduk Enak",
                "Nasi Uduk Gitu-Gitu Aja?\nCobain Nasi Uduk disini,\n\nNasi Uduk Enak!\n\nwalau Mahal Tapi Enak..\n\n",
                "Jln.Laper Banget no 7\n" + "Bandung\n" + "Indonesia",
                "-6.890388", "107.610272",
                "-6.923640" ,"107.623833"
        ));
        addItem(new DummyItem("4",
                "Minuman Segarrr",
                "Udara Panas, Kepanasan\nHarus Banyak Minum,\n\nMinuman Segarrrr!\n\nTersegarrr yang pernah anda rasakan\n\n",
                "Jln.Haus Terus no 76\n" + "Bandung\n" + "Indonesia",
                "-6.890388", "107.610272",
                "-6.923640" ,"107.623833"
        ));
        addItem(new DummyItem("5",
                "Semua Ada",
                "Mau Makan Apa?\nMau Minum Apa?\n\nSemua Ada!\n\nHarga Terjangkau\n\n",
                "Jln.Tambah Baru no 100\n" + "Bandung\n" + "Indonesia",
                "-6.890388", "107.610272",
                "-6.293640" ,"107.673436"
        ));
    }

    public static void addItem(DummyItem item) {
        ITEMS.add(0, item);
        ITEM_MAP.put(item.id, item);
    }

    public static List getItemAll(){
        return ITEMS;
    }

    public static DummyItem getItem(int idx){
        return ITEMS.get(idx);
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class DummyItem {
        public String id;
        public String content;
        public String detail;
        public String alamat;
        public String sLat;
        public String sLong;
        public String dLat;
        public String dLong;
        public boolean isNew;

        public DummyItem(String id, String content, String detail, String alamat, String sLat, String sLong, String dLat, String dLong) {
            this.id = id;
            this.content = content;
            this.detail = detail;
            this.alamat = alamat;
            this.sLat = sLat;
            this.sLong = sLong;
            this.dLat = dLat;
            this.dLong = dLong;
            this.isNew = true;
        }

        @Override
        public String toString() {
            return content;
        }
    }
}
