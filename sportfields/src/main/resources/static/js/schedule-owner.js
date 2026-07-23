const schedule_facility =
document.getElementById("schedule-facility");

const courtHeader =
document.getElementById("courtHeader");



schedule_facility.addEventListener("change",function(){


    let facilityId = this.value;


    fetch("/schedule/calendar/" + facilityId)


    .then(response=>response.json())


    .then(data=>{


        console.log(data);


        courtHeader.innerHTML="";


        data.forEach(court=>{


            courtHeader.innerHTML += `

             <div class="py-10 px-8 border-r border-outline-variant/10 text-center hover:bg-surface-container-lowest transition-colors">
                                   <span class="block font-headline-md text-primary text-lg font-black tracking-tight leading-none mb-1">
                                        ${court.courtName}
                                   </span>
                     </div>

            `;


        });


    });


});