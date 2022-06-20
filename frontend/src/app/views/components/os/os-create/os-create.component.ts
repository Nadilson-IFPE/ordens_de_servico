import { Component, OnInit } from "@angular/core";
import { TecnicoService } from "src/app/services/tecnico.service";
import { Tecnico } from "src/app/models/tecnico";
import { ClienteService } from "src/app/services/cliente.service";
import { Cliente } from "./../../../../models/cliente";

@Component({
  selector: "app-os-create",
  templateUrl: "./os-create.component.html",
  styleUrls: ["./os-create.component.css"],
})
export class OsCreateComponent implements OnInit {
  selected = "";

  tecnicos: Tecnico[] = [];
  clientes: Cliente[] = [];

  constructor(
    private tecnicoService: TecnicoService,
    private clienteService: ClienteService
  ) {}

  ngOnInit(): void {
    this.listarTecnicos();
    this.listarClientes();
  }

  listarTecnicos(): void {
    this.tecnicoService.findAll().subscribe((resposta) => {
      this.tecnicos = resposta;
    });
  }

  listarClientes(): void {
    this.clienteService.findAll().subscribe((resposta) => {
      this.clientes = resposta;
    });
  }
}
