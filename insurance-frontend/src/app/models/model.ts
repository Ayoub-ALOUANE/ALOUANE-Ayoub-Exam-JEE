export interface Client {
    id?: number;
    nom: string;
    email: string;
}

export interface Contract {
    id?: number;
    type: 'AUT' | 'HAB' | 'SAN';
    dateSouscription?: string;
    statut: 'EN_COURS' | 'VALIDE' | 'RESILIE';
    dateValidation?: string;
    montantCotisation: number;
    duree: number;
    tauxCouverture: number;
    client?: Client;
}

export interface AutomobileContract extends Contract {
    matricule: string;
    marque: string;
    modele: string;
}

export interface HabitationContract extends Contract {
    typeLogement: 'APPARTEMENT' | 'MAISON' | 'LOCAL_COMMERCIAL';
    adresse: string;
    superficie: number;
}

export interface HealthContract extends Contract {
    niveauCouverture: 'BASIQUE' | 'INTERMEDIAIRE' | 'PREMIUM';
    nombrePersonnes: number;
}

export interface Payment {
    id?: number;
    date?: string;
    montant: number;
    type: 'MENSUALITE' | 'PAIEMENT_ANNUEL' | 'PAIEMENT_EXCEPTIONNEL';
}
