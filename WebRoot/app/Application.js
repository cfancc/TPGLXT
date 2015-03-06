Ext.application({
	name : 'TPGLXT',
	controllers : ['yonghuGL', 'toupiaoDXGL', 'toupiaoJG'],
	launch : function() {
		Ext.create('Ext.container.Viewport', {
			layout : {
				type : 'border'
			},
			items : [{
				xtype : 'box',
				region : 'north',
				html : '<br/><div style="font-weight: bolder;font-size:25px;text-align:center;color:#FFFFFF">投票系统后台管理</div><br/>'
			}, {
				xtype : 'panel',
				region : 'west',
				width : 150,
				title : '欢迎您的登录!',
				bodyStyle : 'padding-bottom:15px;background:#eee;',
				autoScroll : true,
				collapsible : true,
				dockedItems : [{
							xtype : 'toolbar',
							dock : 'top',
							layout : {
								pack : 'end'
							},
							items : [{
										text : '注销',
										handler:function(){
											window.location.href = "index.jsp";
										}
									}]
						}],
				html : '<br><h3>功能简介:</h3><h4 class="details-info">这个页面是投票管理系统的后台界面可以对投票用户和投票对象进行添加、删除、修改及查询操作，并可以对投票结果进行统计，图形化显示投票结果</h4>'

			}, {
				xtype : 'tabpanel',
				region : 'center',
				items : [{
							xtype : 'yonghuGLPanel'
						}, {
							xtype : 'toupiaoDXGLPanel'
						}, {
							xtype : 'toupiaoJGPanel'
						}]
			}]
		});
	}
});