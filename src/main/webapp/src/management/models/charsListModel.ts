import CharacteristicModel from "./characteristic.model";
import CharGroupModel from "./char-group.model";

export default class CharsListModel{
    characteristicGroupId: number;
    characteristicGroupName: string;
    characteristics: CharacteristicModel[];
}