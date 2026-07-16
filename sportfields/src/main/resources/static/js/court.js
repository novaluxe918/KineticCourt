  document.getElementById("facilitySelect").addEventListener("change", function () {
        const facilityId = this.value;

        if (facilityId) {
            window.location.href = "/court/court_owner?facilityId=" + facilityId;
        } else {
            window.location.href = "/court/court_owner";
        }
    });
