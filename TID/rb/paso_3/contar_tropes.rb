
def contar_tropes(conjuntos, clasificacion)
  # Por cada serie,
  # por cada uno de sus tropes
  conjuntos.each do |serie|
    serie.tropes.each do |trope|

      # 'Cargamos' el genero del trope.
      generoDelTrope = clasificacion[trope]

      # Incrementamos al conteo de dicho genero en esta serie.
      serie.puntuacionGeneros[generoDelTrope] = serie.puntuacionGeneros[generoDelTrope] + 1
    end
  end
  return conjuntos
end
