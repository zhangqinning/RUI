package com.music.lib_api.model.song

class SongDetailBean {
    /**
     * songs : [{"name":"JoJo","id":27917548,"pst":0,"t":0,"ar":[{"id":48514,"name":"Boz Scaggs","tns":[],"alias":[]}],"alia":[],"pop":85,"st":0,"rt":"","fee":8,"v":6,"crbt":null,"cf":"","al":{"id":2694934,"name":"The Essential Boz Scaggs","picUrl":"https://p1.music.126.net/_YmTuIMoG0dh2OJSOE-LPw==/17829680556434243.jpg","tns":[],"pic_str":"17829680556434243","pic":17829680556434244},"dt":354746,"h":{"br":320000,"fid":0,"size":14220536,"vd":-2.65076E-4},"m":{"br":160000,"fid":0,"size":7123590,"vd":0.0324002},"l":{"br":96000,"fid":0,"size":4284811,"vd":-2.65076E-4},"a":null,"cd":"2","no":1,"rtUrl":null,"ftype":0,"rtUrls":[],"djId":0,"copyright":1,"s_id":0,"mark":0,"mv":0,"mst":9,"cp":7001,"rtype":0,"rurl":null,"publishTime":1382976000007}]
     * privileges : [{"id":27917548,"fee":8,"payed":0,"st":0,"pl":128000,"dl":0,"sp":7,"cp":1,"subp":1,"cs":false,"maxbr":999000,"fl":128000,"toast":false,"flag":256,"preSell":false}]
     * code : 200
     */
    var code = 0
    var songs: List<SongsBean>? = null
    var privileges: List<PrivilegesBean>? = null

    class SongsBean {
        /**
         * name : JoJo
         * id : 27917548
         * pst : 0
         * t : 0
         * ar : [{"id":48514,"name":"Boz Scaggs","tns":[],"alias":[]}]
         * alia : []
         * pop : 85
         * st : 0
         * rt :
         * fee : 8
         * v : 6
         * crbt : null
         * cf :
         * al : {"id":2694934,"name":"The Essential Boz Scaggs","picUrl":"https://p1.music.126.net/_YmTuIMoG0dh2OJSOE-LPw==/17829680556434243.jpg","tns":[],"pic_str":"17829680556434243","pic":17829680556434244}
         * dt : 354746
         * h : {"br":320000,"fid":0,"size":14220536,"vd":-2.65076E-4}
         * m : {"br":160000,"fid":0,"size":7123590,"vd":0.0324002}
         * l : {"br":96000,"fid":0,"size":4284811,"vd":-2.65076E-4}
         * a : null
         * cd : 2
         * no : 1
         * rtUrl : null
         * ftype : 0
         * rtUrls : []
         * djId : 0
         * copyright : 1
         * s_id : 0
         * mark : 0
         * mv : 0
         * mst : 9
         * cp : 7001
         * rtype : 0
         * rurl : null
         * publishTime : 1382976000007
         */
        var name: String? = null
        var id: Long = 0
        var pst = 0
        var t = 0
        var pop = 0
        var st = 0
        var rt: String? = null
        var fee = 0
        var v = 0
        var crbt: Any? = null
        var cf: String? = null
        var al: AlBean? = null

        //歌曲时间
        var dt: Long = 0
        var h: HBean? = null
        var m: MBean? = null
        var l: LBean? = null
        var a: Any? = null
        var cd: String? = null
        var no = 0
        var rtUrl: Any? = null
        var ftype = 0
        var djId = 0
        var copyright = 0
        var s_id = 0
        var mark: Long = 0
        var mv: Long = 0
        var mst = 0
        var cp = 0
        var rtype = 0
        var rurl: Any? = null
        var publishTime: Long = 0
        var ar: List<ArBean?>? = null
        var alia: List<*>? = null
        var rtUrls: List<*>? = null
        var recommendReason: String? = null
        fun setMv(mv: Int) {
            this.mv = mv.toLong()
        }

        class AlBean {
            /**
             * id : 2694934
             * name : The Essential Boz Scaggs
             * picUrl : https://p1.music.126.net/_YmTuIMoG0dh2OJSOE-LPw==/17829680556434243.jpg
             * tns : []
             * pic_str : 17829680556434243
             * pic : 17829680556434244
             */
            var id: Long = 0
            var name: String? = null
            var picUrl: String? = null
            var pic_str: String? = null
            var pic: Long = 0
            var tns: List<*>? = null
        }

        class HBean {
            /**
             * br : 320000
             * fid : 0
             * size : 14220536
             * vd : -2.65076E-4
             */
            var br = 0
            var fid: String? = null
            var size = 0
            var vd = 0.0
        }

        class MBean {
            /**
             * br : 160000
             * fid : 0
             * size : 7123590
             * vd : 0.0324002
             */
            var br = 0
            var fid: String? = null
            var size = 0
            var vd = 0.0
        }

        class LBean {
            /**
             * br : 96000
             * fid : 0
             * size : 4284811
             * vd : -2.65076E-4
             */
            var br = 0
            var fid: String? = null
            var size = 0
            var vd = 0.0
        }

        class ArBean {
            /**
             * id : 48514
             * name : Boz Scaggs
             * tns : []
             * alias : []
             */
            private var id: Long = 0
            var name: String? = null
            var tns: List<*>? = null
            var alias: List<*>? = null
            fun getId(): String {
                return id.toString()
            }

            fun setId(id: Long) {
                this.id = id
            }
        }
    }

    class PrivilegesBean {
        /**
         * id : 27917548
         * fee : 8
         * payed : 0
         * st : 0
         * pl : 128000
         * dl : 0
         * sp : 7
         * cp : 1
         * subp : 1
         * cs : false
         * maxbr : 999000
         * fl : 128000
         * toast : false
         * flag : 256
         * preSell : false
         */
        var id: Long = 0
        var fee = 0
        var payed = 0
        var st = 0
        var pl = 0
        var dl = 0
        var sp = 0
        var cp = 0
        var subp = 0
        var isCs = false
        var maxbr = 0
        var fl = 0
        var isToast = false
        var flag = 0
        var isPreSell = false
        override fun toString(): String {
            return "PrivilegesBean{" +
                    "id=" + id +
                    ", fee=" + fee +
                    ", payed=" + payed +
                    ", st=" + st +
                    ", pl=" + pl +
                    ", dl=" + dl +
                    ", sp=" + sp +
                    ", cp=" + cp +
                    ", subp=" + subp +
                    ", cs=" + isCs +
                    ", maxbr=" + maxbr +
                    ", fl=" + fl +
                    ", toast=" + isToast +
                    ", flag=" + flag +
                    ", preSell=" + isPreSell +
                    '}'
        }
    }

    override fun toString(): String {
        return "SongDetailBean{" +
                "code=" + code +
                ", songs=" + songs +
                ", privileges=" + privileges +
                '}'
    }
}