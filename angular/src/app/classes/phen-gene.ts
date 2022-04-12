export class PhenGene {
    
    constructor(data:Array<string>)
    {
        this.data = new Array<string>();
        this.phen_gene = data[0];
        this.phen_rank = data[1];
        this.phen_score = data[2];
        this.phen_status = data[3];
    }

    public describe(): Array<string> {
        return Object.getOwnPropertyNames(this).splice(1);
    }

    public getAllFields(): Array<string>{
        this.data[0] = this.phen_gene;
        this.data[1] = this.phen_rank;
        this.data[2] = this.phen_score;
        this.data[3] = this.phen_status;
        return this.data;
    }
    
    data:Array<string>;
    phen_gene:string;
    phen_rank:string;
    phen_score:string;
    phen_status:string;


}