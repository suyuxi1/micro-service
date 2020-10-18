<template>
	<view class="container">
		<view>
			<uni-segmented-control :current="current" :values="menus" @clickItem="onClickItem" style-type="text" active-color="#EC544D"></uni-segmented-control>
			<view class="content">
				<view v-if="current === 0">
					<p class="notice">{{ notice.content }}</p>
					<uni-search-bar></uni-search-bar>
					<uni-list>
						<uni-list-item
							:title="item.title"
							:note="item.summary"
							:thumb="item.cover"
							thumb-size="lg"
							:rightText="item.downloadUrl === null ? '兑换' : '下载'"
							v-for="(item, index) in shares"
							:key="index"
							class="item"
							@click="gotoDetail(item.downloadUrl, item.id)"
						/>
					</uni-list>
				</view>
				<view v-if="current === 1">
					<uni-card
						title="微服务技术"
						mode="title"
						:is-shadow="true"
						thumbnail="https://img-cdn-qiniu.dcloud.net.cn/uniapp/images/muwu.jpg"
						extra="技术没有上限"
						note="软件_1851"
					>
						使用说明
					</uni-card>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
import uniSegmentedControl from '@/components/uni-segmented-control/uni-segmented-control.vue';
import uniSearchBar from '@/components/uni-search-bar/uni-search-bar.vue';

import { request, get } from '@/utils/request';
import { SHARE_LIST_URL, NOTICE_URL } from '@/utils/api';
export default {
	components: { uniSegmentedControl, uniSearchBar },
	data() {
		return {
			menus: ['发现', '使用说明'],
			current: 0,
			shares: [],
			notice: {},
			pageNo: 1,
			pageSize: 6,
			more: true
		};
	},
	onLoad() {
		//请求前加载动画
		uni.showLoading({
			title: '加载中'
		});
		this.getNotice();
		this.getShares(true);
		//请求结束延时隐藏加载动画
		setTimeout(() => {
			uni.hideLoading();
		}, 1000);
	},
	onShow() {
		this.getShares(true);
	},
	//触底加载更多
	onReachBottom() {
		//没有更多数据了
		if (!this.more) {
			uni.showToast({
				title: '已加载完毕',
				duration: 1000
			});
			//直接返回，不要执行下面代码了
			return false;
		}
		//正常加载下一页
		this.pageNo = this.pageNo + 1;
		uni.showLoading({
			title: '加载中'
		});
		//不带参数都请求，就是分页加载
		this.getShares();
		setTimeout(() => {
			uni.hideLoading();
		}, 1000);
	},
	//下拉刷新
	onPullDownRefresh() {
		//参数为true，就是重新开始加载
		this.getShares(true);
	},
	methods: {
		onClickItem(e) {
			if (this.current !== e.currentIndex) {
				this.current = e.currentIndex;
			}
		},
		async getShares(init) {
			//入参为true，从第一页重新开始加载数据，场景为：下拉刷新、每次进入
			if (init) {
				this.pageNo = 1;
				this.more = true;
			}
			//请求远程shares列表数据，不用then了，这样调用就行
			let res = await request(SHARE_LIST_URL, 'GET', {
				pageNo: this.pageNo,
				pageSize: this.pageSize
			});
			console.log(res.data)
			//处理进入首页加载数据和下拉刷新场景
			if (init) {
				//获得新的数据集合，将数据初始化为本次请求返回结果
				this.shares = res.data;
				//停止下拉刷新
				uni.stopPullDownRefresh();
			} else {
				//不是下拉刷新，是加载下一页数据，则把新数据追加到后面，不能覆盖原值
				this.shares = this.shares.concat(res.data);
			}
			//加载到了最后一页
			if (res.data.length < this.pageSize && this.pageNo > 0) {
				this.more = false;
			}
		},
		async getNotice() {
			//公告
			let notice = await get(NOTICE_URL);
			// console.log(notice.data);
			this.notice = notice.data;
		},
		gotoDetail(downloadUrl, id) {
			if (downloadUrl === null) {
				uni.navigateTo({
					url: '../../home/exchange/exchange?id=' + id
				});
			} else {
				uni.navigateTo({
					url: '../../home/share-detail/share-detail?id=' + id
				});
			}
		}
	}
};
</script>

<style>
.notice {
	margin-top: 10px;
	margin-left: 10px;
	font-size: 16px;
	color: #ec544d;
}
</style>
