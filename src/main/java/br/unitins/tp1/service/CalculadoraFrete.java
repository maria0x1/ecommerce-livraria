package br.unitins.tp1.service;

@FunctionalInterface
public interface CalculadoraFrete {

    Double calcularFrete(String cepDestino, Double pesoKg);
}
