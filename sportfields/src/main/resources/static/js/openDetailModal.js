
        function openDetailModal(button){

   let id = button.dataset.id;

   fetch("/facility/detail/" + id)

   .then(response => response.json())

   .then(data => {

   document.getElementById("facilityName").innerText =
   data.name_facility;

   document.getElementById("facilityAddress").innerText =
   data.address;

   document.getElementById("facilityPhone").innerText =
   data.phone;

    document.getElementById("facilityDescription").innerText =
    data.description;

    document.getElementById("facilityImage").src =
    data.img_url;

    document.getElementById("facilityWard").innerText =
    data.wards;


   document.getElementById("detailModal")
   .classList.remove("hidden");

   })

   .catch(error => {

   console.log(error);

   });

   }

   function closeDetailModal(){

   document.getElementById("detailModal")
   .classList.add("hidden");

   }