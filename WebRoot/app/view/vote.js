Ext.Loader.setConfig({
			enabled : true
		});
Ext.onReady(function() {
	Ext.create('Ext.Viewport', {
		layout : 'border',
		title : 'Ext Layout Browser',
		items : [{
			xtype : 'box',
			region : 'north',
			html : '<br/><div style="font-weight: bolder;font-size:25px;text-align:center;color:#FFFFFF">在线投票页面</div><br/>'
		}, {
			xtype : 'panel',
			region : 'center',
			margins : '20 150 80 150',
			layout : 'border',
			items : [{
						xtype : 'gridpanel',
						region : 'west',
						width : 220,
						title : '得票结果公示区',
						columns : [{
									text : '得票人',
									dataIndex : 'xm'
								}, {
									text : '得票数',
									dataIndex : 'dps'
								}],
						store : Ext.create('Ext.data.Store', {
									fields : ['xm', 'dps'],
									sorters : [{
												property : 'dps',
												direction : 'DESC'
											}],
									proxy : {
										// store的代理
										type : 'ajax', // 类型为ajax，异步请求数据
										api : {
											read : 'touPiaoTongJiAction'// 读取数据的url
										},
										reader : {
											type : 'json', // 解析数据的类型，这里认为从后台来的数据是json类型
											root : 'toupiaojglist'// json的root
										}
									},
									autoLoad : true
								})
					}, {
						xtype : 'form',
						title : '投票区',
						region : 'center',
						layout : 'fit',
						items : [{
									xtype : 'fieldset',
									title : '投票名单',
									margin : 15,
									items : [{
												xtype : 'radiogroup',
												columns : 2,
												vertical : true,
												defaults : {
													margin : 5
												},
												items : []
											}]
								}]
					}],
			buttons : [{
				text : '投票',
				handler : function(button) {
					Ext.Msg.confirm("提示", "确认投票?", function(buttonId) {
						if (buttonId == "yes") {
							var formpanel = button.up('panel').down('form');
							if (formpanel.down('radio[checked=true]')) {
								var xm = formpanel.down('radio[checked=true]').boxLabel;
								var dps = formpanel.down('radio[checked=true]').dps;
								var tpdxid = formpanel
										.down('radio[checked=true]').tpdxid
								Ext.Ajax.request({
											url : 'touPiaoAction',// 这里url为投票
											async : false,
											params : {
												xm : xm,
												dps : dps,
												tpdxid : tpdxid
												// 传的是 "名字"
											},
											success : function(response, opts) {
												button.up('viewport')
														.down('grid')
														.getStore().load();
												var rt = Ext
														.decode(response.responseText);
												Ext.Msg.alert("提示", rt.msg);
											},
											failure : function(response, opts) {
												Ext.Msg.alert("提示",
														"投票失败！请重新操作!");
											}
										});
							} else {
								Ext.Msg.alert("提示", "请先选择投票对象!");
							}

						}
					});

				}
			}, {
				text : '退出投票',
				handler : function() {
					window.location.href = "index.jsp";
				}
			}],
			listeners : {
				beforerender : function(panel) {
					// 然后需要查询到后台的数据,应该是一个包含年份的数组
					var items = panel.down('radiogroup').items;
					Ext.Ajax.request({
						url : 'touPiaoTongJiAction',
						async : false,
						success : function(response, opts) {
							var votelist = Ext.JSON
									.decode(response.responseText).toupiaojglist;// 后台连接时启用这个,把注释开开,注掉下面的votelist
							var radiogroup = panel.down('radiogroup');
							for (i = 0; i < votelist.length; i++) {
								// 将name赋给radio
								var radio = Ext.widget('radio');
								radio.boxLabel = votelist[i].xm;
								radio.name = 'vote', radio.dps = votelist[i].dps;
								radio.tpdxid = votelist[i].tpdxid;
								radio.boxLabelAlign = 'before';
								items.add(radio);
							}
						},
						failure : function(response, opts) {
							Ext.Msg.alert("提示", "投票对象列表加载失败！");
						}
					});
				}
			}

		}]
	})
})