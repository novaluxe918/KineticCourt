const schedule_facility =
document.getElementById("schedule-facility");
const scheduleBody = document.getElementById("scheduleBody");
const courtHeader =
document.getElementById("courtHeader");



schedule_facility.addEventListener("change",function(){


    let facilityId = this.value;


    fetch("/schedule/calendar/" + facilityId)


    .then(response=>response.json())


    .then(data => {

        console.log(data);

        // Xóa dữ liệu cũ
        courtHeader.innerHTML = "";
        scheduleBody.innerHTML = "";

        // Render header sân
        data.forEach(court => {

            courtHeader.innerHTML += `
                <div class="py-10 px-8 border-r border-outline-variant/10 text-center hover:bg-surface-container-lowest transition-colors">
                    <span class="block font-headline-md text-primary text-lg font-black tracking-tight leading-none mb-1">
                        ${court.courtName}
                    </span>
                </div>
            `;

        });

        // Render slot
        data.forEach((court, courtIndex) => {

            court.slots.forEach(slot => {

                let card = "";

                // AVAILABLE
                if(slot.status === "AVAILABLE"){
                    card = `
                        <div class="rounded-2xl bg-emerald-50 border-2 border-emerald-100 p-5 h-full">
                            <span class="font-black text-emerald-700">Free</span>
                            <p>${slot.time_start} - ${slot.time_end}</p>
                        </div>
                    `;
                }

                // BOOKED
                if(slot.status === "BOOKED"){
                    card = `
                        <div class="rounded-2xl bg-primary p-5 text-white h-full">
                            <span class="font-black">Booked</span>
                            <p>${slot.time_start} - ${slot.time_end}</p>
                        </div>
                    `;
                }

                // MAINTENANCE
                if(slot.status === "MAINTENANCE"){
                    card = `
                        <div class="rounded-2xl bg-slate-50 border-2 border-slate-200 p-5 h-full">
                            <span class="font-black text-slate-700">Bảo trì</span>
                            <p>${slot.time_start} - ${slot.time_end}</p>
                        </div>
                    `;
                }

                scheduleBody.innerHTML += `
                    <div class="p-3 border-r border-b border-outline-variant/5"
                         style="grid-column:${courtIndex + 2}; grid-row:${slot.rowStart} / span ${slot.rowSpan};">
                        ${card}
                    </div>
                `;

            });

        });

    });


});