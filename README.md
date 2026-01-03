# FinBatch Core — Financial Batch Processing Engine

![Java](https://img.shields.io/badge/Java-17%2B-007396?style=flat-square&logo=openjdk&logoColor=white)
![Build](https://img.shields.io/badge/Build-Maven-C71A36?style=flat-square&logo=apachemaven&logoColor=white)
![Tests](https://img.shields.io/badge/Tests-JUnit5-25A162?style=flat-square&logo=junit5&logoColor=white)
![Logging](https://img.shields.io/badge/Logging-SLF4J-black?style=flat-square)
![License](https://img.shields.io/badge/License-MIT-blue?style=flat-square)

## Descripción Ejecutiva

FinBatch Core es un motor de procesamiento por lotes (Batch Engine) de alto rendimiento diseñado para la ingesta, validación y consolidación de transacciones financieras.

El sistema simula el ciclo de vida de procesamiento de datos en instituciones bancarias, abordando la necesidad crítica de procesar grandes volúmenes de información (Flat Files) garantizando la integridad de los datos, la trazabilidad de errores y la inmutabilidad de los registros financieros. A diferencia de soluciones basadas en scripts simples, este proyecto implementa una arquitectura modular y robusta, preparada para auditoría y escalabilidad.

## Objetivos Técnicos

El diseño del sistema demuestra competencias avanzadas en ingeniería de software aplicada a finanzas:

1.  **Arquitectura Limpia:** Separación estricta de responsabilidades (Input, Model, Validation, Service) para facilitar el mantenimiento.
2.  **Inmutabilidad de Datos:** Uso de Java Records para garantizar que las transacciones validadas no puedan ser alteradas en memoria.
3.  **Manejo de Precisión Monetaria:** Implementación exclusiva de `BigDecimal` para evitar errores de coma flotante inherentes a `double`.
4.  **Procesamiento Eficiente (Streams):** Uso de Java NIO y Streams para procesar archivos línea por línea, optimizando el uso de memoria (RAM).
5.  **Observabilidad:** Sistema de logging estructurado (SLF4J) con niveles de severidad (INFO, WARN, ERROR) para monitoreo en producción.
6.  **Calidad de Código:** Cobertura de pruebas unitarias (JUnit 5) para asegurar la lógica de validación crítica.

## Arquitectura del Sistema

El flujo de datos sigue un pipeline lineal con puntos de control de calidad (Quality Gates).

```mermaid
graph LR
    A[Input CSV] -->|Stream Reading| B(CsvReader)
    B --> C{FinancialValidator}
    C -- Valid --> D[Mapper & Processor]
    C -- Invalid --> E[Error Log / Reject]
    D -->|Aggregations| F[BatchSummary]
    F --> G[Final Report]
