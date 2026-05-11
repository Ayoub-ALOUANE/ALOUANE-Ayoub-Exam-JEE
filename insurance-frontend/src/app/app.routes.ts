import { Routes } from '@angular/router';
import { LoginComponent } from './login/login';
import { ClientsComponent } from './clients/clients';
import { ClientContractsComponent } from './client-contracts/client-contracts';
import { SubscribeContractComponent } from './subscribe-contract/subscribe-contract';
import { authGuard } from './guards/auth-guard';

export const routes: Routes = [
    { path: "login", component: LoginComponent },
    { path: "clients", component: ClientsComponent, canActivate: [authGuard] },
    { path: "client-contracts/:clientId", component: ClientContractsComponent, canActivate: [authGuard] },
    { path: "subscribe-contract/:clientId", component: SubscribeContractComponent, canActivate: [authGuard] },
    { path: "", redirectTo: "/clients", pathMatch: "full" }
];
