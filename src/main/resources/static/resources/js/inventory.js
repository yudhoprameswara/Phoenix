(function(){
    inventoryButtonListener();
}());

function inventoryButtonListener(){
    $(".inventory-menu").click(function(event){
        $('.main-content').html('<h1>Loading</h1>')
        getInventoryIndexHtml()
        getInventoryRows(1);
     
    });
}

function getInventoryIndexHtml(){
    $('.main-content').html(
        `
        <a href="javascript:;" class="create-button new-inventory">Add new Inventory</a>
            <table class="table-content">
                <thead>
                    <tr>
                        <th>Action</th>
                        <th>Name</th>
                        <th>Stock</th>
                        <th>Description</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>
                            <a href="javascript:;" class="edit-button">Edit</a>
                            <a href="javascript:;" class="edit-button">Delete</a>
                        </td>
                        <td class="name-row">Name</td>
                        <td class="stock-row">Stock</td>
                        <td class="description-row">Description</td>
                    </tr>
                </tbody>
                <tfoot>
                <tr>
                <td colspan="4">
                    <div class="pagination">
                        <div><span class="currentPage"> </span> of <span class="totalPages"></span></div>
                        <div class="pages"></div>
                    </div>
                </td>
            </tr>
                </tfoot>
            </table>
        `
    ) 
}
function getInventoryRows(page){
    $.ajax({
        url:`http://localhost:7777/api/inventory/index?page=${page}`,
        success: function({content,totalPages}){
           let pageObject = {grid: content, totalPages : totalPages};
           renderGridInventory(pageObject,page)
           addPageNumberListenerInventory();
        }
    });
}

function renderGridInventory({grid, totalPages}, currentPage){
    $(".currentPage").text(currentPage);
    $(".totalPages").text(totalPages);
    let htmlPages = "";
    for(let i = 1; i<=totalPages; i++ ){
        let pages = `<div class="page-link">${i}</div>`
        htmlPages = htmlPages + pages 
    }
    $(".pages").html(htmlPages);

    let htmlString = "";
    for(let{inventoryName,stock,description} of grid){
        let row = `<tr>
        <td>
            <a href="javascript:;" class="edit-button" data-id=${inventoryName}>edit</a>
        </td>
        <td class="inventoryName-row">${inventoryName}</td>
        <td class="stock-row">${stock}</td>
        <td class="description-row">${description}</td>
    </tr>`
    htmlString = htmlString + row;    
    }
    $('.table-content tbody').html(htmlString);
}

function addPageNumberListenerInventory(){
    $(".page-link").click(function(event){
       let page =  $(this).text();
       getInventoryRows(page);
    });
}

