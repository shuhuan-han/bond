 <nav class="ease2">
    <ul>
        <li class="blank-head">
            <a href="/home/index/index"></a>
        </li>
      	
      	<li class="category-12 catg">
            <a href="/product_list.jsp?cat=0" target="_top">
                <i class="nav-icons">
                    <img src="/home/imgs/bond_sort.png" alt="债券分类"></i>
                <h3>债券分类</h3>
           </a>
        </li>
      	<#list goodsCategorys as goodsCategory>
      	<#if goodsCategory.parent??>
        <#else>
        <li class="category-${goodsCategory.id} catg">
            <a href="" target="_top">
                <i class="nav-icons">
                    <img src="/home/imgs/top_left_sort.png" alt="${goodsCategory.name}"></i>
                <h3>${goodsCategory.name}</h3>
            </a>
            <div class="sub-nav">
                <span>
                <#list goodsCategorys as secondGoodsCategory>
                <#if secondGoodsCategory.parent??>
                <#if secondGoodsCategory.parent.id == goodsCategory.id>
                    <a href="" target="_top">${secondGoodsCategory.name}</a>
                </#if>
                </#if>
                </#list>
                </span>
            </div>
        </li>
        </#if>
        </#list>
    </ul>
</nav>