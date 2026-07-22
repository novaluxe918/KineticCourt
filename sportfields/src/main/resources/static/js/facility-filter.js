const facility = document.getElementById("facility");
const court = document.getElementById("court");

const summaryFacility = document.getElementById("summaryFacility");
const summaryCourt = document.getElementById("summaryCourt");


// Chọn cơ sở
facility.addEventListener("change", function () {

    let facilityId = this.value;

    let options = court.querySelectorAll("option");


    // Hiển thị tên cơ sở
    if(this.value === ""){
        summaryFacility.innerText = "Chưa chọn";
    }
    else {
        summaryFacility.innerText =
            this.options[this.selectedIndex].text;
    }


    // Lọc sân theo cơ sở
    options.forEach(option => {

        if(option.value === ""){
            option.style.display = "block";
            return;
        }


        if(option.dataset.facility === facilityId){
            option.style.display = "block";
        }
        else {
            option.style.display = "none";
        }

    });


    // Reset sân khi đổi cơ sở
    court.value = "";

    summaryCourt.innerText = "Chưa chọn";

});



// Chọn sân
court.addEventListener("change", function () {

    if(this.value === ""){
        summaryCourt.innerText = "Chưa chọn";
    }
    else {
        summaryCourt.innerText =
            this.options[this.selectedIndex].text;
    }

});