<template>
    <div class="middleDiv ">
        <div class="pay_inner ">
            <div class="pay-item" style="font-weight: bold;">
                订单详情
            </div>
            <div class="pay-item">
                订单id：{{ order.id }}
            </div>
            <div class="pay-item">
                商品名称：{{ order.goodName }}
            </div>
            <div class="pay-item">
                订单价格：{{ order.goodPrice }}￥
            </div>
            <div class="pay-item">
                订单状态：{{ orderStatusText }}
            </div>
            <div class="pay-item">
                用户id：{{ order.userId }}
            </div>
            <div class="pay-item">
                商品id：{{ order.goodId }}
            </div>
            <div class="pay-item">
                地址：暂无地址提示
            </div>
            <div class="pay-btn">
                立即支付
            </div>
        </div>
        <div class="jumpBack " @click="jumpToList">&lt;- 返回</div>
    </div>
</template>

<script>
import { mapActions } from 'vuex';

export default {
    data() {
        return {
            oid: 1,
            order: {},
            orderStatusText: '',
        }
    },
    methods: {
        ...mapActions("order", ["queryOrderById"]),
        jumpToList() {
            this.$router.push('/index')
        }
    },
    computed: {},
    mounted: async function () {
        this.oid = parseInt(window.localStorage.getItem('orderId'))
        console.log('detail-orderId', this.oid);
        this.order = await this.queryOrderById(this.oid)
        console.log('dingdan:', this.order);
        // 支付状态
        var status = this.order.orderStatus
        switch (status) {
            case 0: {
                this.orderStatusText = '订单未支付'
                break
            }
            case 1: {
                this.orderStatusText = '订单已支付'
                break
            }
            case 2: {
                this.orderStatusText = '订单已发货'
                break
            }
            case 3: {
                this.orderStatusText = '订单已收货'
                break
            }
            case 4: {
                this.orderStatusText = '订单已退款'
                break
            }
            case 5: {
                this.orderStatusText = '订单已完成'
                break
            }
        }
    }
}
</script>

<style lang="less">
.middleDiv {
    position: relative;
    top: 2.5%;
    left: 8%;
    width: 84%;
    height: 95%;
    box-shadow: 5px 5px 20px gray;

    .pay_inner {
        position: absolute;
        margin: auto;
        top: 0;
        bottom: 0;
        left: 0;
        right: 0;
        height: 70%;
        width: 30%;

        .pay-item {
            // position: absolute;
            width: 100%;
            height: 60px;
            text-align: left;
            line-height: 60px;
            font-size: 20px;

        }

        .pay-btn {
            width: 100%;
            height: 60px;
            background-color: #00ccff;
            color: #fff;
            text-align: center;
            line-height: 60px;
            font-size: 20px;
        }

    }

    .jumpBack {
        width: 8%;
        height: 35px;
        font-size: 22px;
        text-align: center;
        line-height: 35px;
        position: absolute;
        color: #830000;
        transition: all 0.5s;
        right: 5%;
        top: 5%;
        cursor: pointer;
    }

    .jumpBack:hover {
        color: #c50000;
    }
}
</style>
