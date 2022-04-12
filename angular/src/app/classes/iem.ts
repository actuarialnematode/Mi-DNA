import { IemSymptom } from './iem-symptom';

export class Iem {
        iem_geneid:string='';
        iem_omim:string='';
        iemSymptoms: Set<IemSymptom>= new Set(); 
}
