function dynamicDropDown(listindex)
{
    let element = document.getElementById("action");
    let defaultOption = new Option("Select action","")
    defaultOption.selected = true
    defaultOption.disabled = true
    defaultOption.hidden = true
    switch (listindex)
    {
        case "account" :
            removeOptions(element)
            element.options[0]=defaultOption;
            element.options[1]=new Option("Balance","balance");
            element.options[2]=new Option("Multi-Balance","balancemulti");
            element.options[3]=new Option("Transaction List","txlist");
            element.options[4]=new Option("Transaction List Internal","txlistinternal");
            element.options[5]=new Option("Token Transaction","tokentx");
            element.options[6]=new Option("Token NFT Transaction","tokennfttx");
            element.options[7]=new Option("Mined Blocks","getminedblocks");
            break
        case "transaction" :
            removeOptions(element)
            element.options[0]=defaultOption;
            element.options[1]=new Option("Status","getstatus");
            element.options[2]=new Option("Receipt Status","gettxreceiptstatus");
            break;
    }
    return true;
}

function removeOptions(selectElement) {
    var i, L = selectElement.options.length - 1;
    for(i = L; i >= 0; i--) {
        selectElement.remove(i);
    }
}