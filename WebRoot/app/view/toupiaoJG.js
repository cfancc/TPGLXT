Ext.define('TPGLXT.view.toupiaoJG', {
	extend : 'Ext.panel.Panel',
	alias : 'widget.toupiaoJGPanel',
	title : '投票结果统计',
	layout : 'fit',
	initComponent : function() {
		var me = this;
		Ext.applyIf(me, {
					tbar : [{
						text : '刷新结果',
						handler : function(button) {
							button.up('panel').down('chart').getStore()
									.reload();
						}
					}],
					items : [{
						xtype : 'chart',
						animate : true,
						store : Ext.create('Ext.data.Store', {
									fields : ['xm', 'dps'],
									autoLoad:true,
									proxy : {
										// store的代理
										type : 'ajax', // 类型为ajax，异步请求数据
										api : {
											read : 'touPiaoTongJiAction'// 读取统计数据的url
										},
										reader : {
											type : 'json', // 解析数据的类型，这里认为从后台来的数据是json类型
											root : 'toupiaojglist'// json的root
										}
									}
								}),
						axes : [{
							type : 'Numeric',
							position : 'bottom',
							fields : ['dps'],
							label : {
								renderer : Ext.util.Format
										.numberRenderer('0,0')
							},
							title : '得票数',
							grid : true,
							minimum : 0
						}, {
							type : 'Category',
							position : 'left',
							fields : ['xm'],
							title : '得票人'
						}],
						series : [{
							type : 'bar',
							axis : 'bottom',
							highlight : true,
							tips : {
								trackMouse : true,
								width : 140,
								height : 28,
								renderer : function(storeItem, item) {
									this.setTitle(storeItem.get('xm') + ': '
											+ storeItem.get('dps') + ' 票');
								}
							},
							label : {
								display : 'insideEnd',
								field : 'dps',
								renderer : Ext.util.Format.numberRenderer('0'),
								orientation : 'horizontal',
								color : '#333',
								'text-anchor' : 'middle'
							},
							xField : 'xm',
							yField : 'dps'
						}]
					}]
				});
		me.callParent(arguments);
	}
});