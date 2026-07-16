const facility = document.getElementById("facility");
const court = document.getElementById("court");


facility.addEventListener("change", function () {

    let facilityId = this.value;


    let options = court.querySelectorAll("option");


    options.forEach(option => {


        // option mặc định
        if(option.value === ""){

            option.style.display = "block";
            return;

        }


        let courtFacility =
            option.dataset.facility;


        if(courtFacility === facilityId){

            option.style.display = "block";

        }else{

            option.style.display = "none";

        }

    });


    // reset sân đã chọn
    court.value = "";

});