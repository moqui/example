define("ExampleItems", {
    /* This software is in the public domain under CC0 1.0 Universal plus a Grant of Patent License. */
    data: function () { return {
        exampleId: null,
        exampleItemList: [],
        addDescription: null, addAmount: null, addAmountUomId: null
    } },
    methods: {
        addItem: function () {
            // TODO: ajax call to add item
            // TODO: ajax call to get fresh item list (for exampleItemSeqId and generally in case server changes any data we sent it, there is other data, etc)
            // NOTE: best ajax call would be submit data and get new list in response, single ajax call instead of two
            this.exampleItemList.push({ description:this.addDescription, amount:this.addAmount, amountUomId:this.addAmountUomId });
        }
    },
    watch: {
        addColumnDate:function () {console.log(this.addColumnDate);}
    },
    mounted: function () {
        this.exampleId = this.$root.currentParameters.exampleId;
        if (this.exampleId && this.exampleId.length) {
            var vm = this;
            $.ajax({ type:"GET", url:"/apps/example/Example/DynamicExampleItems/getExampleItemList",
                     data:{ exampleId:this.exampleId }, dataType:"json", headers:{ Accept:'application/json' },
                     error:moqui.handleAjaxError,
                     success: function(responseObj, status, jqXHR) { vm.exampleItemList = responseObj; }});
        }
    }
});
