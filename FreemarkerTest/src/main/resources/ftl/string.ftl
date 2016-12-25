<ul class="left-menu">
	<a id="welcome" href="#/welcome" style="visibility: hidden;display: none;" title="»¶Ó­Ò³">»¶Ó­</a>
	<#list menus as menu>
		<#if menu.parentIds?split(",")?size==2>
			<li class="">
				<a href="javascript:;"> <img src="${menu.iconFileName}" /> <span class="title">${menu.name}</span> <span class="arrow glyphicon glyphicon-chevron-down"></span> </a>
				<ul class="sub-menu" >
					<#list menus as menu1>
						<#if menu1.parentIds?split(",")?size==3&&menu1.parentId==menu.id>
							<li>
								<a href="javascript:;"> <span class="title">${menu1.name}</span> <span class="arrow glyphicon glyphicon-chevron-down"></span> </a>
								
								<#list menus as menu2>
									<ul class="sub-menu2">
										<#if menu2.parentIds?split(",")?size==4&&menu2.parentId==menu1.id>
											<li>
												<a href="#/menumgr/menulist" >${menu2.name}</a>
											</li>
										</#if>
									</ul>
								</#list>
							<li>
						</#if>
					</#list>
				</ul>
			</li>
		</#if>
	</#list>
</ul>
