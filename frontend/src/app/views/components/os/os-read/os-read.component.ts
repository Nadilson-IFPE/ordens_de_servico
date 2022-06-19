import { Component, OnInit, ViewChild } from "@angular/core";
import { AfterViewInit } from "@angular/core";
import { MatTableDataSource } from "@angular/material/table";
import { OS } from "./../../../../models/os";
import { MatPaginator } from "@angular/material/paginator";
import { MatSort } from "@angular/material/sort";
import { OsService } from "./../../../../services/os.service";
import { Router } from "@angular/router";
import { TecnicoService } from "src/app/services/tecnico.service";
import { ClienteService } from "./../../../../services/cliente.service";

@Component({
  selector: "app-os-read",
  templateUrl: "./os-read.component.html",
  styleUrls: ["./os-read.component.css"],
})
export class OsReadComponent implements AfterViewInit {
  listaDeOS: OS[] = [];

  displayedColumns: string[] = [
    "tecnico",
    "cliente",
    "abertura",
    "fechamento",
    "prioridade",
    "status",
    "action",
  ];
  dataSource = new MatTableDataSource<OS>(this.listaDeOS);

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) ordenar!: MatSort;

  constructor(
    private service: OsService,
    private router: Router,
    private tecnicoService: TecnicoService,
    private clienteService: ClienteService
  ) {}

  ngAfterViewInit() {
    this.findAll();

    this.paginator._intl.itemsPerPageLabel = "Itens por página";
    this.paginator._intl.firstPageLabel = "Página inicial";
    this.paginator._intl.lastPageLabel = "Última página";
    this.paginator._intl.previousPageLabel = "Página anterior";
    this.paginator._intl.nextPageLabel = "Próxima página";
    this.paginator._intl.getRangeLabel = (
      page: number,
      pageSize: number,
      length: number
    ) => {
      if (length === 0 || pageSize === 0) {
        return `0 de ${length}`;
      }
      if (pageSize >= length) {
        return `1 de 1`;
      }
      length = Math.max(length, 0);
      const startIndex = page * pageSize;

      startIndex < length
        ? Math.min(startIndex + pageSize, length)
        : startIndex + pageSize;
      return `${startIndex + 1} de ${length}`;
    };
  }

  findAll(): void {
    this.service.findAll().subscribe((resposta) => {
      this.listarTecnico();
      this.listarCliente();
      this.listaDeOS = resposta;
      this.dataSource = new MatTableDataSource<OS>(this.listaDeOS);
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.ordenar;
    });
  }

  navigateToCreate(): void {
    this.router.navigate(["/os/create"]);
  }

  listarTecnico(): void {
    this.listaDeOS.forEach((x) => {
      this.tecnicoService.findById(x.tecnico).subscribe((resposta) => {
        x.tecnico = resposta.nome;
      });
    });
  }

  listarCliente(): void {
    this.listaDeOS.forEach((x) => {
      this.clienteService.findById(x.cliente).subscribe((resposta) => {
        x.cliente = resposta.nome;
      });
    });
  }
}
