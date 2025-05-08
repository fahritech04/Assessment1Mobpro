package com.raihanfahrifi3009.assessment1mobpro.ui.screen

import androidx.lifecycle.ViewModel
import com.raihanfahrifi3009.assessment1mobpro.model.BankData

class BankViewModel : ViewModel() {

    val data = listOf(
        BankData(
            1,
            "Bank Mandiri",
            "Salah satu bank BUMN terbesar di Indonesia yang menyediakan layanan perbankan lengkap untuk ritel, korporat, dan UMKM, dikenal dengan layanan digital Livin’ by Mandiri.",
            "2025-01-12 09:24:37"
        ),
        BankData(
            2,
            "Bank BNI (Bank Negara Indonesia)",
            "Bank milik pemerintah yang berfokus pada layanan komersial dan internasional, serta memiliki sejarah panjang sejak sebelum kemerdekaan Indonesia.",
            "2025-03-08 15:47:21"
        ),
        BankData(
            3,
            "Bank BCA (Bank Central Asia)",
            "Bank swasta terbesar di Indonesia, terkenal dengan layanan customer service unggul dan inovasi digital seperti BCA mobile dan myBCA.",
            "2025-04-19 06:58:13"
        ),
        BankData(
            4,
            "Bank BRI (Bank Rakyat Indonesia)",
            "Bank BUMN yang fokus pada segmen mikro, kecil, dan menengah, serta terkenal dengan jaringan terluas hingga ke desa-desa.",
            "2025-02-25 20:11:42"
        ),
        BankData(
            5,
            "Bank Jago",
            "Bank digital berbasis aplikasi yang menawarkan layanan keuangan modern tanpa kantor cabang, menargetkan segmen milenial dan digital-savvy.",
            "2025-05-01 13:35:50"
        ),
        BankData(
            6,
            "Bank BSI (Bank Syariah Indonesia)",
            "Merupakan hasil merger beberapa bank syariah BUMN, menjadi bank syariah terbesar di Indonesia dengan layanan berbasis prinsip-prinsip Islam.",
            "2025-03-16 23:04:18"
        ),
        BankData(
            7,
            "Bank DKI",
            "Bank milik Pemerintah Provinsi DKI Jakarta, fokus pada pembiayaan pembangunan daerah dan mendukung program-program Pemprov DKI.",
            "2025-04-02 17:22:59"
        ),
        BankData(
            8,
            "Bank BTN (Bank Tabungan Negara)",
            "BUMN yang dikenal sebagai pemimpin pembiayaan perumahan di Indonesia, terutama melalui program KPR untuk masyarakat.",
            "2025-01-30 07:40:05"
        ),
        BankData(
            9,
            "Bank Danamon",
            "Bank swasta yang menawarkan beragam produk perbankan, termasuk pinjaman, tabungan, dan layanan bisnis, serta bagian dari MUFG Group Jepang.",
            "2025-02-08 11:56:33"
        ),
        BankData(
            10,
            "Bank CIMB Niaga",
            "Bank swasta yang merupakan bagian dari CIMB Group Malaysia, dikenal dengan layanan digital OCTO Mobile dan fokus pada perbankan konsumer dan komersial.",
            "2025-04-25 18:14:29"
        )
    )

}