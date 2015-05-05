
# hash: { trope => { genero => conteo } }
# ...Es un hash anidado!!!

#
# series = generos = [nombre:"pim" tropes:["a","b"], nombre:"pam" tropes:["b","c"]]
#
def clasificar_tropes_usando_series(series, hash = Hash.new { |h,k| h[k] = Hash.new(0) })

  series.each do |serie|
    serie.tropes.each do |trope|
      # Incrementamos
      hash[trope][serie.genero] = hash[trope][serie.genero] + 1
    end
  end

  binding.pry
  return hash
end
