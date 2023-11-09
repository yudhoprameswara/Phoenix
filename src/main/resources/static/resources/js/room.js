(function(){
    roomButtonListener();
}());

function roomButtonListener(){
    $(".room-menu").click(function(event){
        getRoomIndexHtml();
        getRoomTypeDropdown();
        getRoomRows("",null,"",1);
        addSearchRoomButtonListener();
    });
}

function getRoomIndexHtml(){
    $('.main-content').html(
        `
        <div class="filter-container"> 
            <input type="text" placeholder="Room.." class="room-filter">

            <select class="roomType-dropdown dropdown">
                <option class="blank-option" value = "no"> Not Specified room type</option>
            </select>

            <select class="status-dropdown dropdown">
                <option value = "no" >Not Specified status </option>
                <option value = "Booked" >Booked</option>
                <option value = "Vacant" >Vacant</option>
            </select>

            <button class="search-button search-room">Search</button>
        </div>
        <a href="javascript:;" class="create-button new-inventory">Add new Room</a>
            <table class="table-content">
                <thead>
                    <tr>
                        <th>Action</th>
                        <th>Number</th>
                        <th>Floor</th>
                        <th>Type</th>
                        <th>Guest Limit</th>
                        <th>Cost per Day</th>
                        <th>Status</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>
                            <a href="javascript:;" class="edit-button">Edit</a>
                            <a href="javascript:;" class="edit-button">Item</a>
                        </td>
                        <td class="name-row">Name</td>
                        <td class="stock-row">Stock</td>
                        <td class="description-row">Description</td>
                        <td class="name-row">Name</td>
                        <td class="stock-row">Stock</td>
                        <td class="description-row">Description</td>
                    </tr>
                </tbody>
                <tfoot>
                <tr>
                <td colspan="7">
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

function getRoomRows(roomNumber,roomType,status,page){
    let roomtypeParameter =roomType===  null?'': `roomType=${roomType}`
    $.ajax({
        url:`http://localhost:7777/api/room/index?roomNumber=${roomNumber}&${roomtypeParameter}&status=${status}&page=${page}`,
        success: function({content,totalPages}){
           let pageObject = {grid: content, totalPages : totalPages};
           renderGridRoom(pageObject,page)
           addPageNumberListenerRoom();
        }
    });
}

function addPageNumberListenerRoom(){
    $(".page-link").click(function(event){
       let page =  $(this).text();

           let roomNumber = $('.room-filter').val();
       let type = $('.roomType-dropdown').children("option:selected").val();
       let status = $('.status-dropdown').children("option:selected").val();
       if (status == "Booked"){
        selectedStatus = true
       }
       else if(status == "Vacant"){
        selectedStatus = false
       }
       else{
        selectedStatus = "";
       } 

       if(type == "no" ){
        selectedType = null
       }
       else{
        selectedType = type;
       }
       getRoomRows(roomNumber,selectedType,selectedStatus,page);
    });
}


function renderGridRoom({grid, totalPages}, currentPage){
    $(".currentPage").text(currentPage);
    $(".totalPages").text(totalPages);
    let htmlPages = "";
    for(let i = 1; i<=totalPages; i++ ){
        let pages = `<div class="page-link">${i}</div>`
        htmlPages = htmlPages + pages 
    }
    $(".pages").html(htmlPages);

    let htmlString = "";
    for(let{number,floor,type,guestLimit,costPerDay,status} of grid){
        let statusText = "";
        if (status == true){
            statusText = "Booked"
        }
        else{
            statusText = "Vacant"
        }

        let row = `<tr>
        <td>
            <a href="javascript:;" class="edit-button" data-id=${number}>Edit</a>
            <a href="javascript:;" class="edit-button detail-inventory" data-id=${number}>Item</a>
        </td>
        <td class="number-row">${number}</td>
        <td class="floor-row">${floor}</td>
        <td class="type-row">${type}</td>
        <td class="guestLimit-row">${guestLimit}</td>
        <td class="costPerDay-row">${costPerDay}</td>
        <td class="status-row">${statusText}</td>
    </tr>`
    htmlString = htmlString + row;    
    }
    $('.table-content tbody').html(htmlString);
    itemRoomButtonListener();
}

function getRoomTypeDropdown(){
    $.ajax({
        url:`http://localhost:7777/api/room/roomType/dropdown`,
        success: function(response){
            for(let{value,text} of response){
                var option =`
                    <option value = ${value} >${text}</option>
                `
                $(option).insertAfter('.blank-option')    
            } 
               
        }
    })
}

function addSearchRoomButtonListener(){
    $(".search-room").click(function(event){
       let roomNumber = $('.room-filter').val();
       let type = $('.roomType-dropdown').children("option:selected").val();
       let status = $('.status-dropdown').children("option:selected").val();
       if (status == "Booked"){
        selectedStatus = true
       }
       else if(status == "Vacant"){
        selectedStatus = false
       }
       else{
        selectedStatus = "";
       } 



       if(type == "no" ){
        selectedType = null
       }
       else{
        selectedType = type;
       }
       getRoomRows(roomNumber,selectedType,selectedStatus,1);
    });
}

function itemRoomButtonListener(){
    $(".detail-inventory").click(function(event){
        let stringHtml = `
        <table class="detail-room-inventory">
        <tr>
            <td>Room Number</td>
            <td class="room-number-detail"></td>
        </tr>

        <tr>
            <td>Room Floor</td>
            <td class="room-floor-detail"></td>
        </tr>

        <tr>
            <td>Room Type</td>
            <td class="room-type-detail"></td>
        </tr>

        <tr>
            <td>Guest Limit</td>
            <td class="guest-limit-detail"></td>
        </tr>
    </table>
    <a href="javascript:;" class="create-button">Add New Item</a>

    <table class="table-content">
        <thead>
            <tr>
                <th>Action</th>
                <th>Inventory</th>
                <th>Stock</th>
                <th>Quantity</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>
                    <a href="javascript:;" class="edit-button">Remove</a>
                </td>
                <td class="inventory-row">inventory</td>
                <td class="stock-row">Stock</td>
                <td class="quantity-row">Quantity</td>
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
    $('.main-content').html(stringHtml);
    let roomNumber = $(this).attr("data-id");
    getDetailRoom(roomNumber);   
    getRoomInventoryRow(roomNumber,1) 
    })
}

function getDetailRoom(roomNumber){
    $.ajax({
        url:`http://localhost:7777/api/room/detail/${roomNumber}`,
        success: function({roomNumber,floor,roomType,guestLimit}){
           $(".room-number-detail").text(roomNumber);
           $(".room-floor-detail").text(floor);
           $(".room-type-detail").text(roomType);
           $(".guest-limit-detail").text(guestLimit);
        }
    })
}

function getRoomInventoryRow(roomNumber,page){
    $.ajax({
        url:`http://localhost:7777/api/room/detailRow/${roomNumber}`,
        success: function({content,totalPages}){
           let pageObject = {grid: content, totalPages : totalPages};
           renderGridRoomInventory(pageObject,page)
           addPageNumberListenerRoomInventory(roomNumber);
        }
    });
}

function addPageNumberListenerRoomInventory(roomNumber){
    $(".page-link").click(function(event){
        let page =  $(this).text();
        getRoomInventoryRow(roomNumber,page);
     });
}

function renderGridRoomInventory({grid, totalPages},currentPage){
        $(".currentPage").text(currentPage);
        $(".totalPages").text(totalPages);
        let htmlPages = "";
        for(let i = 1; i<=totalPages; i++ ){
            let pages = `<div class="page-link">${i}</div>`
            htmlPages = htmlPages + pages 
        }
        $(".pages").html(htmlPages);
    
        let htmlString = "";
        for(let{id,inventoryName,stock,quantity} of grid){
            let row = `<tr>
            <td>
                <a href="javascript:;" class="remove-button remove-inventory" data-id=${id}>Remove</a>
            </td>
            <td class="inventory-room-row">${inventoryName}</td>
            <td class="stock-room-row">${stock}</td>
            <td class="quantity-row">${quantity}</td>
        </tr>`
        htmlString = htmlString + row;    
        }
        $('.table-content tbody').html(htmlString);
        removeItemRoomButtonListener();
    
}

function removeItemRoomButtonListener(){
    $(".remove-inventory").click(function(event){
        let id = $(this).attr("data-id")
        $.ajax({
            url:`http://localhost:7777/api/room/delete/${id}`,
            type:"DELETE",
            success: function(response){
                console.log(response)
            }
        });
        let roomNumber = $(".room-number-detail").text();
        getRoomInventoryRow(roomNumber,1)
    });
}