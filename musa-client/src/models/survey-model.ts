export class ContactRequest {
    personalData: PersonalData
    requestType: any = ''
    text: string = ''
    trackingChecked: boolean = false;
}
  
export class PersonalData {
    country: string = ''
    sex: string = ''
    occupation: string = ''
}