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
                <option class="blank-option" value = ""> Not Specified room type</option>
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
        //    addPageNumberListenerRoom();
        }
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
            <a href="javascript:;" class="edit-button" data-id=${number}>Item</a>
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
       if (status = "Booked"){
        selectedStatus = true
       }
       selectedStatus = false

       if(type == "no" ){
        selectedType = null
       }
       else{
        selectedType = type;
       }
       getRoomRows(roomNumber,selectedType,selectedStatus,1);
    });
}