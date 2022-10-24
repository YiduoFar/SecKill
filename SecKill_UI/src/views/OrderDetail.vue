<template>
    <div class="middleDiv">
        订单详情
    </div>
</template>

<script>
import { mapActions } from 'vuex';

export default {
    data() {
        return {
            oid: 1,
        }
    },
    methods: {
        ...mapActions("order", ["queryOrderById"]),
    },
    computed: {},
    mounted: async function () {
        this.oid = parseInt(window.localStorage.getItem('orderId'))
        console.log('detail-orderId', this.oid);
        this.order = await this.queryOrderById(this.oid)
        console.log('dingdan:', this.order);
    }
}
</script>

<style>
.middleDiv {
    position: relative;
    top: 2.5%;
    left: 8%;
    width: 84%;
    height: 95%;
    box-shadow: 5px 5px 20px gray;
}
</style>
