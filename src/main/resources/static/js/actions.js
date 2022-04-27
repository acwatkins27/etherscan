let addressHtml = document.getElementById("addressContainer");
let tagHtml = document.getElementById("tagContainer");
let startBlockHtml = document.getElementById("startBlockContainer");
let endBlockHtml = document.getElementById("endBlockContainer");
let txHashHtml = document.getElementById("txHashContainer");
let timestampHtml = document.getElementById("timestampContainer");
let startDateHtml = document.getElementById("startDateContainer");
let endDateHtml = document.getElementById("endDateContainer");
let closestHtml = document.getElementById("closestContainer");
let blockNoHtml = document.getElementById("blockNoContainer");
let contractAddressHtml = document.getElementById("contractAddressContainer");
let toHtml = document.getElementById("toContainer");
let valueHtml = document.getElementById("valueContainer");
let gasHtml = document.getElementById("gasContainer");
let gasPriceHtml = document.getElementById("gasPriceContainer");
let blockTypeHtml = document.getElementById("blockTypeContainer");
let containers = [addressHtml, tagHtml, startBlockHtml, endBlockHtml, txHashHtml, timestampHtml, startDateHtml, endDateHtml,
    closestHtml, blockNoHtml, contractAddressHtml, toHtml, valueHtml, gasHtml, gasPriceHtml, blockTypeHtml]

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
            removeOptions(element);
            setContainersToDisplayNone(containers)
            element.disabled = false;
            element.options[0]=defaultOption;
            element.options[1]=new Option("Balance","balance");
            element.options[2]=new Option("Multi-Balance","balancemulti");
            element.options[3]=new Option("Transaction List","txlist");
            element.options[4]=new Option("Transaction List Internal (Address)","txlistinternal");
            element.options[5]=new Option("Transaction List Internal (Hash)","txlistinternal");
            element.options[6]=new Option("Transaction List Internal (Block Range)","txlistinternal");
            element.options[7]=new Option("Token Transaction","tokentx");
            element.options[8]=new Option("Token NFT Transaction","tokennfttx");
            element.options[9]=new Option("Mined Blocks","getminedblocks");
            break
        case "transaction" :
            removeOptions(element);
            setContainersToDisplayNone(containers)
            element.disabled = false;
            element.options[0]=defaultOption;
            element.options[1]=new Option("Status","getstatus");
            element.options[2]=new Option("Receipt Status","gettxreceiptstatus");
            break;
    }
    return true;
}

function addParametersFromSelect(listindex)
{
    switch (listindex)
    {
        case "Balance" :
            setContainersToDisplayNone(containers)
            setElementToDisplayNone(addressHtml)
            setElementToDisplayNone(tagHtml)
            break;
        case "Multi-Balance" :
            setContainersToDisplayNone(containers)
            setElementToDisplayNone(addressHtml)
            setElementToDisplayNone(tagHtml)
            break;
        case "Transaction List" :
            setContainersToDisplayNone(containers)
            setElementToDisplayNone(addressHtml)
            setElementToDisplayNone(startBlockHtml)
            setElementToDisplayNone(endBlockHtml)
            break;
        case "Transaction List Internal (Address)" :
            setContainersToDisplayNone(containers)
            setElementToDisplayNone(addressHtml)
            setElementToDisplayNone(startBlockHtml)
            setElementToDisplayNone(endBlockHtml)
            break;
        case "Transaction List Internal (Hash)" :
            setContainersToDisplayNone(containers)
            setElementToDisplayNone(txHashHtml)

            break;
        case "Transaction List Internal (Block Range)" :
            setContainersToDisplayNone(containers)
            setElementToDisplayNone(startBlockHtml)
            setElementToDisplayNone(endBlockHtml)
            break;
        case "Token Transaction" :
            setContainersToDisplayNone(containers)
            setElementToDisplayNone(contractAddressHtml)
            setElementToDisplayNone(addressHtml)
            setElementToDisplayNone(startBlockHtml)
            setElementToDisplayNone(endBlockHtml)
            break;
        case "Token NFT Transaction" :
            setContainersToDisplayNone(containers)
            setElementToDisplayNone(contractAddressHtml)
            setElementToDisplayNone(addressHtml)
            setElementToDisplayNone(startBlockHtml)
            setElementToDisplayNone(endBlockHtml)
            break;
        case "Mined Blocks" :
            setContainersToDisplayNone(containers)
            setElementToDisplayNone(addressHtml)
            setElementToDisplayNone(blockTypeHtml)
            break;
        case "Status" :
            setContainersToDisplayNone(containers)
            setElementToDisplayNone(txHashHtml)
            break;
        case "Receipt Status" :
            setContainersToDisplayNone(containers)
            setElementToDisplayNone(txHashHtml)
            break;
    }
    return true;
}

function removeOptions(selectElement) {
    let i, L = selectElement.options.length - 1;
    for(i = L; i >= 0; i--) {
        selectElement.remove(i);
    }
}

function setElementToDisplayNone(selectElement) {
    selectElement.setAttribute("style", "");
}

function setContainersToDisplayNone(containers) {
    for (const container of containers) {
        container.setAttribute("style", "display: none")
    }
}