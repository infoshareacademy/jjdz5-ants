function moveRow(tr){
    while (tr.parentNode&&tr.nodeName.toUpperCase()!=='TR'){
        tr=tr.parentNode
    }
    var table1=document.getElementById('sourceTable');
    if (!this.rows){
        var rows=table1.getElementsByTagName('TR');
        this.rows=[];
        for (var z0=0;z0<rows.length;z0++){
            this.rows[z0]=rows[z0];
        }
    }
    var table2=document.getElementById('selectionTable');
    if (tr.parentNode!==table2){
        table2.appendChild(tr);
    }
    else {
        table1.appendChild(tr);
        for (var z0=0;z0<this.rows.length;z0++){
            if (this.rows[z0].parentNode===table1){
                table1.appendChild(this.rows[z0]);
            }
        }
    }
}