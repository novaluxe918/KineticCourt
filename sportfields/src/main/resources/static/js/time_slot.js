
let slotIndex = 1;

function addTimeSlot(){
tinhavg ();
   let container =
      document.getElementById("time-slots-container");
      const summarySlot = document. getElementById("summarySlot");
      summarySlot.innerText = parseInt(summarySlot.innerText) + 1;
   let html =` <div class="time-slot grid grid-cols-12 gap-4 items-end p-4 bg-surface-container-lowest border border-outline-variant rounded-lg transition-all hover:border-primary-container/50">
                                          <div class="col-span-4 space-y-2">
                                              <label class="block font-label-md text-label-md text-secondary">Giờ bắt đầu</label>
                                              <div class="relative">
                                                  <input name="scheduleDetails[${slotIndex}].time_start" class="w-full p-3 bg-white border border-outline-variant rounded-lg font-body-md text-body-md focus:ring-2 focus:ring-primary-container focus:outline-none" type="time" required />
                                              </div>
                                          </div>
                                          <div class="col-span-4 space-y-2">
                                              <label class="block font-label-md text-label-md text-secondary">Giờ kết thúc</label>
                                              <div class="relative">
                                                  <input name="scheduleDetails[${slotIndex}].time_end" class="w-full p-3 bg-white border border-outline-variant rounded-lg font-body-md text-body-md focus:ring-2 focus:ring-primary-container focus:outline-none" type="time" required />
                                              </div>
                                          </div>
                                          <div class="col-span-3 space-y-2">
                                              <label class="block font-label-md text-label-md text-secondary">Đơn giá (1h)</label>
                                              <div class="relative">
                                                  <input  name="scheduleDetails[${slotIndex}].price" class="w-full price-input p-3 pr-12 bg-white border border-outline-variant rounded-lg font-body-md text-body-md focus:ring-2 focus:ring-primary-container focus:outline-none text-right" value="0" type="number" required />
                                                  <span class="absolute right-3 top-1/2 -translate-y-1/2 text-secondary text-xs font-bold">VND</span>
                                              </div>
                                          </div>

                                          <div class="col-span-1 flex justify-center">
                                                                          <button class="delete-slot p-2 text-error hover:bg-error/10 rounded-lg transition-colors" type="button">
                                                                              <span class="material-symbols-outlined" data-icon="delete">delete</span>
                                                                          </button>
                                                                      </div>

                                      </div>`
                                       container.insertAdjacentHTML(
                                              "beforeend",
                                              html
                                          );


                                          slotIndex++;

}
document.addEventListener("input", function(e){

    if(e.target.name && e.target.name.includes(".price")){
        tinhavg();
    }

});

document.addEventListener("click", function (e) {

    const deleteButton = e.target.closest(".delete-slot");



    if (!deleteButton) {
        return;
    }

    const slots = document.querySelectorAll(".time-slot");

    if (slots.length === 1) {
        alert("Phải có ít nhất một khung giờ.");
        return;
    }

    deleteButton.closest(".time-slot").remove();
     summarySlot.innerText = parseInt(summarySlot.innerText) - 1;
    updateSlotIndex();
});

function updateSlotIndex(){

    const slots = document.querySelectorAll(".time-slot");

    slots.forEach((slot,index)=>{

        const inputs = slot.querySelectorAll("input");

        inputs.forEach(input=>{

            input.name = input.name.replace(
                /\[\d+\]/,
                `[${index}]`
            );

        });

    });

    slotIndex = slots.length;
    tinhavg();

}
// tong
function tinhavg() {

    let priceInput = document.querySelectorAll('.price-input');

    let total = 0;
    let count = 0;


    priceInput.forEach(p => {

        let price = Number(p.value);

        if(price > 0){
            total += price;
            count++;
        }

    });


    let avg = 0;

    if(count > 0){
        avg = total / count;
    }


    document.getElementById("summaryPrice").innerText =
        avg.toLocaleString("vi-VN") + " VNĐ";


    console.log("Tổng:", total);
    console.log("Số lượng:", count);
    console.log("Trung bình:", avg);
}