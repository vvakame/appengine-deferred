package net.vvakame.appengine.deferred.factory;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

import net.vvakame.appengine.deferred.annotation.Deferred;
import static javax.lang.model.util.ElementFilter.*;

/**
 * アノテーションプロセッサ本体.
 * 
 * @see ClassGenerateHelper
 * @author vvakame
 */
@SupportedSourceVersion(SourceVersion.RELEASE_6)
@SupportedAnnotationTypes("net.vvakame.appengine.deferred.annotation.*")
public class DeferredAnnotationProcessor extends AbstractProcessor {

	private static final String DEBUG_OPTION = "DeferredDebug";

	@Override
	public synchronized void init(ProcessingEnvironment processingEnv) {
		super.init(processingEnv);
		Log.init(processingEnv.getMessager());

		// デバッグログ出力の設定を行う
		String debug = getOption(DEBUG_OPTION);
		if ("true".equalsIgnoreCase(debug)) {
			Log.setDebug(true);
		} else {
			Log.setDebug(false);
		}

		Log.d("init DeferredAnnotationProcessor");
	}

	@Override
	public boolean process(Set<? extends TypeElement> annotations,
			RoundEnvironment roundEnv) {

		for (Element element : methodsIn(roundEnv
				.getElementsAnnotatedWith(Deferred.class))) {

			Log.d("process " + element.toString());

			ClassGenerateHelper generater = ClassGenerateHelper
					.newInstance(element);

			generater.process();

			// 構文上のエラーに遭遇していたら処理を中断する
			if (generater.isEncountError()) {
				continue;
			}

			try {
				generater.write();
			} catch (IOException e) {
				Log.e(e);
			}
		}

		return false;
	}

	String getOption(String key) {
		Map<String, String> options = processingEnv.getOptions();
		if (options.containsKey(key) && !"".equals(options.get(key))) {
			return options.get(key);
		} else {
			return null;
		}
	}
}
