<template>
	<view class="container">
		<h4 class="title">{{ data.share.title }}</h4>
		<image :src="data.share.cover"></image>
		<p>{{ data.share.summary }}</p>
		<p>{{ data.share.downloadUrl }}</p>
		<button @click="paste">复制下载地址</button>
	</view>
</template>

<script>
import { request } from '@/utils/request';
import { SHARE_URL } from '@/utils/api';
export default {
	data() {
		return {
			data:{
				share: null,
				wxNickname:''
			},			
			id: ''
		};
	},
	onLoad(option) {
		this.id = option.id;
		console.log(this.id);
		this.getShare();
	},
	methods: {
		async getShare() {
			let id = this.id;
			uni.showLoading({
				title: '加载中'
			});
			let res = await request(SHARE_URL + `/${id}`, 'GET', {});
			setTimeout(() => {
				uni.hideLoading();
			}, 100);
			this.data.share = res.data.data.share;
			console.log(this.data);
		},
		paste() {
			uni.setClipboardData({
				data: this.data.share.downloadUrl,
				success: function() {
					uni.showToast({
						title: '下载地址已复制'
					});
				}
			});
		}
	}
};
</script>

<style>
.container {
	padding: 10px;
	font-size: 15px;
}
.title {
	font-size: 16px;
	font-weight: 500;
	margin-bottom: 20px;
}
p {
	margin: 10px;
}
</style>
