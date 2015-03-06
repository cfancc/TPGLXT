Ext.define('TPGLXT.store.yonghuGL', {
			extend : 'Ext.data.Store',
			fields : [{
						name : 'ryid'
					},{
						name : 'xm'
					}, {
						name : 'xh'
					}, {
						name : 'zy'
					}, {
						name : 'lxfs'
					}, {
						name : 'sftp'
					}, {
						name : 'rylb'
					}, {
						name : 'dlm'
					}, {
						name : 'mm'
					}],
			autoLoad:true,
			constructor : function(cfg) {
				var me = this;
				cfg = cfg || {};
				me.callParent([Ext.apply({
							proxy : {// store的代理
								type : 'ajax', // 类型为ajax，异步请求数据
								api : {
									read : 'chaXunYongHuAction'// 读取数据的url
								},
								reader : {
									type : 'json', // 解析数据的类型，这里认为从后台来的数据是json类型
									root : 'yhlist',// json的root
									totalProperty : 'totalSize'
								}
							}
						}, cfg)]);
			}
		});