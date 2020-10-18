<template>
	<view class="container">
		<!-- 标题卡片模式
		<uni-card title="微服务技术" mode="title" :is-shadow="true" thumbnail="https://img-cdn-qiniu.dcloud.net.cn/uniapp/images/muwu.jpg" extra="技术没有上限" note="软件_1851">
			微服务技术微服务技术
		</uni-card> -->
		<view class="card">
			<view class="uni-padding-wrap uni-common-mt" style="display: flex; justify-content: space-around; margin-top: 20upx;">
				<view class="subtitle">是否原创</view>
				<view><switch check @change="switch1Change" /></view>
			</view>
			<view class="item-row">
				<view class="subtitle">标题</view>
				<input type="text" placeholder="请输入标题" class="input-style" v-model="ShareRequestDTO.title" />
			</view>
			<view class="item-row">
				<view class="subtitle">作者</view>
				<input type="text" placeholder="请输入作者" class="input-style" v-model="ShareRequestDTO.author" />
			</view>
			<view class="item-row">
				<view class="subtitle">价格</view>
				<input type="number" placeholder="输入价格" class="input-style" v-model="ShareRequestDTO.price" />
			</view>
			<view class="item-row">
				<view class="subtitle">简介</view>
				<input type="text" placeholder="分享简介" class="input-style" v-model="ShareRequestDTO.summary" />
			</view>
			<view class="item-row">
				<view class="subtitle">下载地址</view>
				<input type="text" placeholder="https://shimo.im/docs/RdjY8hcV3DHJ9dc9" class="input-style" v-model="ShareRequestDTO.downloadUrl" />
			</view>
		</view>
		<button @click="contribute">投稿</button>
	</view>
</template>
<script>
import { request, get } from '@/utils/request';
import { CONTRIBUTE_URL } from '@/utils/api';
export default {
	data() {
		return {
			title: '投稿',	
			ShareRequestDTO: {
				userId: uni.getStorageSync('user').id,
				isOriginal: false,
				title: '',
				author: '',
				price: '',
				summary: '',
				downloadUrl: 'https://shimo.im/docs/RdjY8hcV3DHJ9dc9'
			}
		};
	},
	methods: {
		switch1Change: function(e) {
			this.ShareRequestDTO.isOriginal = e.target.value;
			console.log('开关按钮发生的操作' + this.ShareRequestDTO.isOriginal);
		},
		contribute() {
			uni.showToast({
				title: '投稿'
			});
			request(CONTRIBUTE_URL, 'POST', {
				author: this.ShareRequestDTO.author,
				downloadUrl:  this.ShareRequestDTO.downloadUrl,
				isOriginal: this.ShareRequestDTO.isOriginal,
				price: this.ShareRequestDTO.price,
				summary: this.ShareRequestDTO.summary,
				title: this.ShareRequestDTO.title
			}).then(res => {
				console.log(res);
			});
			console.log(this.ShareRequestDTO)
		}
	}
};
</script>
<style>
.class {
	padding: 30upx;
}
.tip {
	padding: 20upx;
	height: 100upx;
	font-size: 12px;
	border-bottom: 1px solid #dfdfdf;
}
.item-row {
	display: flex;
	padding: 15upx;
	height: 48upx;
	border-bottom: 1px solid #dfdfdf;
}
.flex-around {
	display: flex;
	align-items: center;
	justify-content: space-around;
}
.subtitle {
	font-size: 14px;
	width: 25%;
}
.input-style {
	width: 75%;
}
</style>
