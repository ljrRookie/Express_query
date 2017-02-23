package com.example.express_query;

import java.util.List;

/**
 * Created by user on 2017/2/22.
 */

public class ExpressInfo {


    /**
     * resultcode : 200
     * reason : 成功的返回
     * result : {"company":"韵达","com":"yd","no":"1901758930149","status":"1","list":[{"datetime":"2017-02-19 18:27:34","remark":"到达：上海嘉定区迎园公司新城路街道服务部 已收件","zone":""},{"datetime":"2017-02-19 20:56:47","remark":"到达：上海分拨中心","zone":""},{"datetime":"2017-02-20 02:08:24","remark":"到达：上海分拨中心 发往：广东广州网点包","zone":""},{"datetime":"2017-02-20 02:31:04","remark":"到达：上海分拨中心 发往：广东广州分拨中心","zone":""},{"datetime":"2017-02-21 00:42:18","remark":"到达：广东广州分拨中心 上级站点：上海分拨中心","zone":""},{"datetime":"2017-02-21 00:51:04","remark":"到达：广东广州分拨中心 发往：广东广州天河区天平架长湴公司","zone":""},{"datetime":"2017-02-21 20:44:15","remark":"到达：广东广州天河区天平架长湴公司元岗分部 指定：大叔(13760629801) 派送","zone":""},{"datetime":"2017-02-21 21:05:00","remark":"到达：广东广州天河区天平架长湴公司元岗分部 由 已签收 签收","zone":""}]}
     * error_code : 0
     */

    public String resultcode;
    public String reason;
    public ResultBean result;
    public int error_code;

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getResultcode() {
        return resultcode;
    }

    public void setResultcode(String resultcode) {
        this.resultcode = resultcode;
    }

    public static class ResultBean {
        /**
         * company : 韵达
         * com : yd
         * no : 1901758930149
         * status : 1
         * list : [{"datetime":"2017-02-19 18:27:34","remark":"到达：上海嘉定区迎园公司新城路街道服务部 已收件","zone":""},{"datetime":"2017-02-19 20:56:47","remark":"到达：上海分拨中心","zone":""},{"datetime":"2017-02-20 02:08:24","remark":"到达：上海分拨中心 发往：广东广州网点包","zone":""},{"datetime":"2017-02-20 02:31:04","remark":"到达：上海分拨中心 发往：广东广州分拨中心","zone":""},{"datetime":"2017-02-21 00:42:18","remark":"到达：广东广州分拨中心 上级站点：上海分拨中心","zone":""},{"datetime":"2017-02-21 00:51:04","remark":"到达：广东广州分拨中心 发往：广东广州天河区天平架长湴公司","zone":""},{"datetime":"2017-02-21 20:44:15","remark":"到达：广东广州天河区天平架长湴公司元岗分部 指定：大叔(13760629801) 派送","zone":""},{"datetime":"2017-02-21 21:05:00","remark":"到达：广东广州天河区天平架长湴公司元岗分部 由 已签收 签收","zone":""}]
         */

        public String company;
        public String com;
        public String no;
        public String status;
        public List<ListBean> list;

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getCom() {
            return com;
        }

        public void setCom(String com) {
            this.com = com;
        }

        public String getNo() {
            return no;
        }

        public void setNo(String no) {
            this.no = no;
        }

        public static class ListBean {
            /**
             * datetime : 2017-02-19 18:27:34
             * remark : 到达：上海嘉定区迎园公司新城路街道服务部 已收件
             * zone :
             */

            public String datetime;
            public String remark;
            public String zone;

            public String getDatetime() {
                return datetime;
            }

            public void setDatetime(String datetime) {
                this.datetime = datetime;
            }

            public String getZone() {
                return zone;
            }

            public void setZone(String zone) {
                this.zone = zone;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }
        }
    }
}
