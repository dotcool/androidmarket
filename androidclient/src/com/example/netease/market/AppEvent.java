package com.example.netease.market;

import com.example.netease.market.bean.Item;

public class AppEvent {

	public static class OnPkgAddedEvent {

		private String packageName ;
		public OnPkgAddedEvent(String pkg){
			packageName = pkg ;
		}
		public String getPkg(){
			return packageName ;
		}
	}

	public static class OnPkgRemovedEvent {

		private String packageName ;
		public OnPkgRemovedEvent(String pkg){
			packageName = pkg ;
		}
		public String getPkg(){
			return packageName ;
		}
	}

	public static class OnBaseEvent {

		public Item item;

		public Item getItem() {
			return item;
		}
	}

	public static class OnDownloadStartEvent extends OnBaseEvent {

		public OnDownloadStartEvent(Item item) {
			this.item = item;
		}
	}

	public static class OnDownloadingEvent extends OnBaseEvent {
		public OnDownloadingEvent(Item item) {
			this.item = item;
		}
	}

	public static class OnDownloadFinishEvent extends OnBaseEvent {

		public OnDownloadFinishEvent(Item item) {
			this.item = item;
		}
	}

	public static class OnDownloadCancelEvent extends OnBaseEvent {

		public OnDownloadCancelEvent(Item item) {
			this.item = item;
		}
	}

	public static class OnApkStartEvent extends OnBaseEvent {
		public OnApkStartEvent(Item item) {
			this.item = item;
		}
	}

	public static class OnApkInstallEvent extends OnBaseEvent {
		public OnApkInstallEvent(Item item) {
			this.item = item;
		}
	}
}
